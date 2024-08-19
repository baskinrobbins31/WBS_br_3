package ssg.library.script;

public class OutboundScript extends Script{

  /** 출고관리 메뉴(관리자) */
  public void printOutboundMenuAdmin() {
    sb.append("\n---출고 관리 메뉴---\n")
        .append("\t").append("1. 출고요청승인\t\t").append("2. 출고지시서 조회\t\t").append("3. 출고상품검색\t\t").append("4. 출고리스트 조회\n")
        .append("\t").append("5. 출고리스트 검색\t\t").append("6. 배차관리\t\t").append("7. 운송장 관리\t\t").append("8. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 출고관리 메뉴(회원) */
  public void printOutboundMenuMember() {
    sb.append("\n---출고 관리 메뉴---\n")
        .append("\t").append("1. 출고요청\t\t").append("2. 출고지시서 조회\t\t").append("3. 출고상품검색\t\t").append("4. 출고리스트 조회\n")
        .append("\t").append("5. 출고리스트 검색\t\t").append("6. 운송장 관리\t\t").append("7. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 배차관리 메뉴(관리자) */
  public void printAdminVehicleAssignmentMenu() {
    sb.append("\n---배차 관리 메뉴---\n")
        .append("\t").append("1. 배차등록\t\t").append("2. 배차조회\t\t").append("3. 배차수정\t\t").append("4. 배차취소\n")
        .append("\t").append("5. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 운송장 관리 메뉴(관리자) */
  public void printAdminWaybillMenu() {
    sb.append("\n---운송장 관리 메뉴---\n").append("\t")
        .append("1.운송장 등록\t\t").append("2.운송장 조회\t\t").append("3.운송장 수정\t\t").append("4.운송장 검색\n")
        .append("\t").append("5.이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 운송장 관리 메뉴(회원) */
  public void printMemberWaybillMenu() {
    sb.append("\n---운송장 관리 메뉴---\n").append("\t")
        .append("1.운송장 조회\t").append("2.이전화면\n");
    printString(sb);
    printInputNumber();
  }

}
