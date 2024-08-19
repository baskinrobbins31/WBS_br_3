package ssg.library.script;

public class OutboundScript extends Script{

  /** 출고관리 메뉴(관리자) */
  public void printOutboundMenuAdmin() {
    sb.append("---출고 관리 메뉴---\n")
        .append("\t").append("1. 출고요청승인\t").append("2. 출고지시서 조회\t").append("3. 출고상품검색\t").append("4. 출고리스트 조회\n")
        .append("\t").append("5. 출고리스트 검색\n").append("6. 배차관리\t").append("7. 운송장 관리\t").append("8. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 출고관리 메뉴(회원) */
  public void printOutboundMenuMember() {
    sb.append("---출고 관리 메뉴---\n")
        .append("\t").append("1. 출고요청\t").append("2. 출고지시서 조회\t").append("3. 출고상품검색\t").append("4. 출고리스트 조회\n")
        .append("\t").append("5. 출고리스트 검색\t").append("6. 운송장 관리\t").append("7. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 배차관리 메뉴(관리자) */
  public void printAdminVehicleAssignmentMenu() {
    sb.append("---배차 관리 메뉴---\n")
        .append("\t").append("1. 배차등록\t").append("2. 배차조회\t").append("3. 배차수정\t").append("4. 배차취소\n")
        .append("\t").append("5. 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 운송장 관리 메뉴(관리자) */
  public void printAdminWaybillMenu() {
    index =1;
    sb.append("---운송장 관리 메뉴---\n").append("\t")
        .append(index++).append(". 운송장 등록\t").append(index++).append(". 운송장 조회\t").append(index++).append(". 운송장 수정\t").
        append(index++).append(". 운송장 검색\n").append("\t").append(index++).append(". 이전화면\n");
    printString(sb);
    printInputNumber();
  }

  /** 운송장 관리 메뉴(회원) */
  public void printMemberWaybillMenu() {
    index =1;
    sb.append("---운송장 관리 메뉴---\n").append("\t")
        .append(index++).append(". 운송장 검색\t").append(index++).append(". 이전화면\n");
    printString(sb);
    printInputNumber();
  }

}
