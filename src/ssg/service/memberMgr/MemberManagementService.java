package ssg.service.memberMgr;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import ssg.Main;
import ssg.dao.memberManagement.MemberManagementDao;
import ssg.dto.Member;
import ssg.enums.UserType;
import ssg.library.script.MemberManagementScript;

public class MemberManagementService implements MemberManagementInterface {

  private MemberManagementDao memberManagementDao = new MemberManagementDao();
  private StringBuilder sb = new StringBuilder();
  private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  /** 회원 등록 승인 */
  @Override
  public void confirmMemberCreate(int id) {
    System.out.println("--회원 등록 승인--");
    memberManagementDao.update(id);
  }
  public void confirmMemberList() {
    System.out.println("--등록 대기 회원 조회--");
    List<Member> memberList = memberManagementDao.readConfirmMember();

    for (Member m : memberList) {
      sb.append(m.getId()).append("\t").append(m.getUserid()).append("\t").append(m.getPassWord())
          .append("\t").append(m.getUserName()).append("\t")
          .append(m.getBRN() == null ? "없음" : m.getBRN()).append("\t").append(m.getUserType())
          .append("\t").append(m.getMemberConfirm()).append("\t")
          .append(formatter.format(m.getCreateAt())).append("\t").append(m.getPhoneNumber())
          .append("\t").append(m.getAddress()).append("\t").append(m.getEmail());
      String str = sb.toString();
      System.out.println(str);
      sb.delete(0, sb.length());
    }
  }
  public void confirmMemberAll() {
    System.out.println("--회원등록 전체승인--");
    memberManagementDao.updateAll();
  }


  /** 전체 회원 조회 */
  @Override
  public void listMember() {
    List<Member> memberList = memberManagementDao.read();

    for(Member m: memberList) {
      sb.append(m.getId()).append("\t").append(m.getUserid()).append("\t").append(m.getPassWord())
          .append("\t").append(m.getUserName()).append("\t").append(m.getBRN() == null ? "없음" : m.getBRN()).append("\t").append(m.getUserType())
          .append("\t").append(m.getMemberConfirm()).append("\t").append(formatter.format(m.getCreateAt())).append("\t").append(m.getPhoneNumber())
          .append("\t").append(m.getAddress()).append("\t").append(m.getEmail());
      String str = sb.toString();
      System.out.println(str);
      sb.delete(0, sb.length());
    }

  }

  /** 관리자 조회 */
  @Override
  public void listAdmin(UserType type) {
    List<Member> memberList = memberManagementDao.readType(type);

    for(Member m: memberList) {
      sb.append(m.getId()).append("\t").append(m.getUserid()).append("\t").append(m.getPassWord())
          .append("\t").append(m.getUserName()).append("\t").append(m.getBRN() == null ? "없음" : m.getBRN()).append("\t").append(m.getUserType())
          .append("\t").append(m.getMemberConfirm()).append("\t").append(formatter.format(m.getCreateAt())).append("\t").append(m.getPhoneNumber())
          .append("\t").append(m.getAddress()).append("\t").append(m.getEmail());
      String str = sb.toString();
      System.out.println(str);
      sb.delete(0, sb.length());
    }
  }

  /** 사업자 등록번호 조회 */
  @Override
  public void listBRN(String brn) {
    if (brn == null) {
      System.out.println("사업자번호가 등록되지 않았습니다.");
    }
    else {
      List<Member> memberList = memberManagementDao.readBRN(brn);
      for (Member m : memberList) {
        sb.append(m.getId()).append("\t").append(m.getUserid())
            .append("\t").append(m.getUserName())
            .append("\t").append(m.getBRN() == null ? "없음" : m.getBRN())
            .append("\t").append(m.getUserType())
            .append("\t").append(formatter.format(m.getCreateAt()))
            .append("\t").append(m.getPhoneNumber())
            .append("\t").append(m.getAddress())
            .append("\t").append(m.getEmail());
        String str = sb.toString();
        System.out.println(str);
        sb.delete(0, sb.length());
      }
    }

  }

  /** 회원 권한 수정 */
  @Override
  public void updateAuthority() {

    try {
      MemberManagementScript.getMEMBER_MANAGEMENT_SCRIPT_INSTANCE().printInputID();
      int id = Integer.parseInt(br.readLine());

      System.out.println("-지정할 권한 선택-");
      System.out.println("1.총관리자\t" + "2.창고관리자\t" + "3.사장\t" + "4.일반회원");
      int enumselect = Integer.parseInt(br.readLine());
      UserType type;
      switch (enumselect) {
        case 1 -> type = UserType.ADMINISTRATOR;
        case 2 -> type = UserType.WH_ADMIN;
        case 3 -> type = UserType.PRESIDENT_MEMBER;
        case 4 -> type = UserType.NORMAL_MEMBER;
        default -> throw new NumberFormatException();
      }
      memberManagementDao.updateAuthority(type, id);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /** 회원 삭제 */
  @Override
  public void deleteMember() {
    try {
      System.out.print("삭제할 아이디를 입력하세요. : ");
      int id = Integer.parseInt(br.readLine());
      memberManagementDao.delete(id);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 화원 찾기 */
  @Override
  public void findMember() {

  }

  /** 회원 상세보기 */
  @Override
  public void showInformation() {
    sb.append("ID : ").append(Main.loginOnMember.getId()).append("\n")
        .append("사용자ID : ").append(Main.loginOnMember.getUserid()).append("\n")
        .append("비밀번호 : ").append(Main.loginOnMember.getPassWord()).append("\n")
        .append("사용자 이름 : ").append(Main.loginOnMember.getUserName()).append("\n")
        .append("사업자번호 : ").append(Main.loginOnMember.getBRN() == null ? "없음" : Main.loginOnMember.getBRN()).append("\n")
        .append("회원 등급 : ").append(Main.loginOnMember.getUserType()).append("\n")
        .append("생성일 : ").append(formatter.format(Main.loginOnMember.getCreateAt())).append("\n")
        .append("전화번호 : ").append(Main.loginOnMember.getPhoneNumber()).append("\n")
        .append("주소 : ").append(Main.loginOnMember.getAddress()).append("\n")
        .append("e메일 : ").append(Main.loginOnMember.getEmail()).append("\n");
    String str = sb.toString();
    System.out.println(str);
    sb.delete(0, sb.length());
  }

  /** 회원본인 정보 수정 */
  @Override
  public void updateMember() {
    try {
      System.out.println("--회원 정보 수정--");
      System.out.println("*미수정시 no 입력");
      System.out.print("수정할 비밀번호를 입력하세요. : ");
      String updatePass = br.readLine();
      System.out.print("수정할 이름을 입력하세요. : ");
      String updateName = br.readLine();
      System.out.print("수정할 사업자번호를 입력하세요. : ");
      String updateBRN = br.readLine();
      System.out.print("수정할 전화번호를 입력하세요. : ");
      String updatePhone = br.readLine();
      System.out.print("수정할 주소를 입력하세요. : ");
      String updateAddress = br.readLine();
      System.out.print("수정할 이메일을 입력하세요. : ");
      String updateEmail = br.readLine();

      Member temp = Member.builder().passWord(updatePass.equals("no")? Main.loginOnMember.getPassWord() : updatePass)
          .userName(updateName.equals("no")? Main.loginOnMember.getUserName() : updateName)
          .BRN(updateBRN.equals("no")? Main.loginOnMember.getBRN() : updateBRN)
          .phoneNumber(updatePhone.equals("no")? Main.loginOnMember.getPhoneNumber() : updatePhone)
          .address(updateAddress.equals("no") ? Main.loginOnMember.getAddress() : updateAddress)
          .email(updateEmail.equals("no") ? Main.loginOnMember.getEmail() : updateEmail).build();

      memberManagementDao.update(temp);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
