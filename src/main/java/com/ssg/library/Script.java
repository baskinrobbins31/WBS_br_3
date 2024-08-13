package main.java.com.ssg.library;

public class Script {
  // 출력 메세지 및 스크립트를 포함하고 있는 클래스 입니다.
  // 모두 완성하면 각 파트를 메서드로 재구성해서 Main 클래스에서 사용하도록 합니다.

  StringBuilder sb =new StringBuilder();
  int index = 1;

  public void printString() {
    String str = sb.toString();
    System.out.print(str);
    sb.delete(0, sb.length());
  }

  public void printInputNumber() {
    sb.append("번호를 입력해 주세요 :  ");
    printString();
  }

  /** 처음 실행시 뜨는 화면 */
  public void printStartMenu() {
    sb.append("\t창고천국에 오신 것을 환영합니다!\n\n").
        append("============================================================================\n").
    append("\t").append(index++).append(". 로그인\t").append(index++).append(". 출고관리\t").append(index++).append(". 고객센터\n").
        append("============================================================================\n");
    printString();
    printInputNumber();
    /*System.out.println(
        "============================================================================");
    System.out.println("=".repeat(70));
    System.out.printf("%40s", "창고천국에 오신 것을 환영합니다!");
    System.out.println();
    System.out.println("1. 로그인  2. 출고 관리  3. 고객센터");
    System.out.println("============================================================================");
    System.out.println(); // 메뉴와 입력 사이에 한 줄 추가하기
    System.out.print("번호를 입력해 주세요 :  ");*/
  }

  /** 로그인 전체 메뉴 */
  public void printLoginAllMenu() {
    index =1;
    sb.append("---로그인 전체 메뉴---\n").append("\t").append(index++).append(". 로그인\t").append(index++).append(". 아이디 찾기\t").
        append(index++).append(". 비밀번호 찾기\t").append(index++).append(". 회원등록\n");
    printString();
    printInputNumber();
  }

  /** 아이디가 중복시 출력문 */
  public void printDuplicateID() {
    sb.append("중복되는 아이디가 있습니다.\n");
    printString();
  }

  /** 아이디가 없을시 출력문 */
  public void printUnknownID() {
    sb.append("해당 아이디가 없습니다.\n");
    printString();
  }

  /** 로그인 정보가 틀렸을떄 출력문 */
  public void printUnknownMember() {
    sb.append("아이디 혹은 비밀번호가 틀렸습니다.\n");
    printString();
  }

  /** 메인 메뉴 */
  public void printMainMenu() {
    index =1;
    sb.append("---메인 메뉴---\n").append("\t").append(index++).append(". 회원관리\t").append(index++).append(". 입고관리").
        append(index++).append(". 재고관리").append(index++).append(". 창고관리").append(index++).append(". 고객센터").append(index++).append(". 로그아웃\n");
    printString();
    printInputNumber();
  }


  /** 회원관리 메뉴(관리자) */
  public void printAdminMemberMenu() {
    index =1;
    sb.append("---회원 관리 메뉴---\n").append("\t").append(index++).append(". 회원등록 승인\t").append(index++).append(". 조회\t").
        append(index++).append(". 회원 상세보기\t").append(index++).append(". 회원수정\n").append("\t").append(index++).append(". 회원삭제\t").
        append(index++).append(". 회원권한 수정\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 회원관리 메뉴(일반회원) */
  public void printEmplMemberMenu() {
    index =1;
    sb.append("---회원 관리 메뉴---\n").append("\t")
        .append(index++).append(". 회원 상세보기\t").append(index++).append(". 회원수정\t").
        append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 회원관리 메뉴(사장) */
  public void printPresMemberMenu() {
    index =1;
    sb.append("---회원 관리 메뉴---\n").append("\t")
        .append(index++).append(". 회원 조회\t").append(index++).append(". 회원 상세보기\t").append(index++).append(". 회원수정\t").
        append(index++).append(". 회원삭제\n").append("\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

/** 출고관리 메뉴(관리자) */
  public void printAdminOutboundMenu() {
    index =1;
    sb.append("---출고 관리 메뉴---\n").append("\t")
        .append(index++).append(". 출고요청승인\t").append(index++).append(". 출고지시서 조회\t").append(index++).append(". 출고상품검색\t").
        append(index++).append(". 출고리스트 조회\n").append("\t").append(index++).append(". 출고리스트 검색\n").append(index++).append(". 배차관리\t").
        append(index++).append(". 운송장 관리\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 출고관리 메뉴(회원) */
  public void printMemberOutboundMenu() {
    index =1;
    sb.append("---출고 관리 메뉴---\n").append("\t")
        .append(index++).append(". 출고요청\t").append(index++).append(". 출고지시서 조회\t").append(index++).append(". 출고상품검색\t").
        append(index++).append(". 출고리스트 조회\n").append("\t").append(index++).append(". 출고리스트 검색\t").
        append(index++).append(". 운송장 관리\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 배차관리 메뉴(관리자) */
  public void printAdminVehicleAssignmentMenu() {
    index =1;
    sb.append("---배차 관리 메뉴---\n").append("\t")
        .append(index++).append(". 배차등록\t").append(index++).append(". 배차조회\t").append(index++).append(". 배차수정\t").
        append(index++).append(". 배차취소\n").append("\t").append(index++).append(". 이전화면\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 운송장 관리 메뉴(관리자) */
  public void printAdminWaybillMenu() {
    index =1;
    sb.append("---운송장 관리 메뉴---\n").append("\t")
        .append(index++).append(". 운송장 등록\t").append(index++).append(". 운송장 조회\t").append(index++).append(". 운송장 수정\t").
        append(index++).append(". 운송장 검색\n").append("\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 운송장 관리 메뉴(회원) */
  public void printMemberWaybillMenu() {
    index =1;
    sb.append("---운송장 관리 메뉴---\n").append("\t")
        .append(index++).append(". 운송장 검색\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 재고 관리 메뉴(관리자) */
  public void printAdminStockMenu() {
    index =1;
    sb.append("---재고 관리 메뉴---\n").append("\t")
        .append(index++).append(". 재고조회\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 재고 관리 메뉴(회원) */
  public void printMemberStockMenu() {
    index =1;
    sb.append("---재고 관리 메뉴---\n").append("\t")
        .append(index++).append(". 재고조회\t").append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 입고 관리 메뉴(관리자) */
  public void printAdminInboundMenu() {
    index =1;
    sb.append("---입고 관리 메뉴---\n").append("\t")
        .append(index++).append(". 입고요청승인\t").append(index++).append(". 입고요청 수정\t").append(index++).append(". 입고요청 취소\t").
        append(index++).append(". 입고지시서 조회\n").append("\t").append(index++).append(". 입고리스트 조회\n").append(index++).append(". 입고현황 조회\t").
        append(index++).append(". 이전화면\n");
    printString();
    printInputNumber();
  }

  /** 입고 관리 메뉴(회원) */
  public void printMemberInboundMenu() {
    index =1;
    sb.append("---입고 관리 메뉴---\n").append("\t")
        .append(index++).append(". 입고요청\t").append(index++).append(". 입고요청 수정\t").append(index++).append(". 입고요청 취소\t").
        append(index++).append(". 입고요청 조회\n").append("\t").append(index++).append(". 입고현황 조회\t").append(". 이전화면\n");
    printString();
    printInputNumber();
  }



  /** 임시 */
  public void temp() {

    System.out.println("<로그인 성공 시>");
    System.out.println("000 관리자님 반갑습니다!");
    System.out.println();
    System.out.println("============================================================================");

  }










}