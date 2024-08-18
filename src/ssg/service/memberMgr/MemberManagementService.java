package ssg.service.memberMgr;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import ssg.Main;
import ssg.dao.memberManagement.memberManagementDao;
import ssg.dto.Member;
import ssg.enums.UserType;

public class MemberManagementService implements MemberManagementInterface {

  private memberManagementDao memberManagementDao = new memberManagementDao();
  private StringBuilder sb = new StringBuilder();
  private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  /** 회원 등록 승인 */
  @Override
  public void confirmMemberCreate() {

  }

  /** 전체 회원 조회 */
  @Override
  public void listMember() {
    ArrayList<Member> memberArrayList = memberManagementDao.readAll();

    for(Member m: memberArrayList) {
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
    ArrayList<Member> memberArrayList = memberManagementDao.readType(type);

    for(Member m: memberArrayList) {
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
  public void listBRN() {

  }

  /** 회원 권한 수정 */
  @Override
  public void updateAuthority() {

  }

  /** 회원 삭제 */
  @Override
  public void deleteMember() {

  }

  /** 화원 찾기 */
  @Override
  public void findMember() {

  }

  /** 회원 상세보기 */
  @Override
  public void showInformation() {

    sb.append(Main.loginOnMember.getId()).append("\t").append(Main.loginOnMember.getUserid()).append("\t").append(Main.loginOnMember.getPassWord())
        .append("\t").append(Main.loginOnMember.getUserName()).append("\t").append(Main.loginOnMember.getBRN() == null ? "없음" : Main.loginOnMember.getBRN())
        .append("\t").append(Main.loginOnMember.getUserType()).append("\t").append(Main.loginOnMember.getMemberConfirm())
        .append("\t").append(formatter.format(Main.loginOnMember.getCreateAt())).append("\t").append(Main.loginOnMember.getPhoneNumber())
        .append("\t").append(Main.loginOnMember.getAddress()).append("\t").append(Main.loginOnMember.getEmail());
    String str = sb.toString();
    System.out.println(str);
    sb.delete(0, sb.length());
  }

  /** 회원 정보 수정 */
  @Override
  public void updateMember() {

  }
}
