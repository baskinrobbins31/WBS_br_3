package ssg.library.script;

import lombok.Getter;

public class MemberManagementScript extends Script{

  StringBuilder sb =new StringBuilder();

  @Getter
  private final static MemberManagementScript MEMBER_MANAGEMENT_SCRIPT_INSTANCE = new MemberManagementScript();

  /** 회원관리 메뉴(관리자) */
  public void printAdminMemberMenu() {
    sb.append("---회원 관리 메뉴---\n")
        .append("\t").append("1. 회원등록 승인\t").append("2. 조회\t").append("3. 회원 상세보기\t").append("4. 회원수정\n")
        .append("\t").append("5. 회원삭제\t").append("6. 회원권한 수정\t").append("7. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 회원관리 메뉴(일반회원) */
  public void printEmplMemberMenu() {
    sb.append("---회원 관리 메뉴---\n").
        append("\t").append("1. 회원 상세보기\t").append("2. 회원수정\t").append("3. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 회원관리 메뉴(사장) */
  public void printPresMemberMenu() {
    sb.append("---회원 관리 메뉴---\n").
        append("\t").append("1. 회원 조회\t").append("2. 회원 상세보기\t").append("3. 회원수정\t").append("4. 회원삭제\n")
        .append("\t").append("5. 이전화면\n");
    printString(sb);
    printInputNumber();
  }
  
  public void printListMenu() {
    sb.append("---회원 조회---\n")
        .append("\t").append("1.전체회원 조회\t").append("2. 관리자 조회\t").append("3. 사업자번호 조회\t").append("4. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  public void printFindAdminMenu() {
    index =1;
    sb.append("---관리자 조회---\n").append("\t")
        .append("1.총관리자\t").append("2. 창고관리자\t").append("3. 사장\t").append("4. 일반직원\n")
        .append("\t").append("5. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

}
