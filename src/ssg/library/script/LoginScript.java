package ssg.library.script;

import lombok.Getter;


public class LoginScript extends Script{

  StringBuilder sb =new StringBuilder();
  int index = 1;

  @Getter
  private static final LoginScript loginScriptInstance = new LoginScript();


  /** 처음 실행시 뜨는 화면 */
  public void printStartMenu() {
    sb.append("\t창고천국에 오신 것을 환영합니다!\n").
        append("============================================================================\n")
        .append("\t").append("1.회원등록\t").append("2.로그인\t").append("3.운송장 조회\t").append("4.고객 센터\n")
        .append("\t").append("5.아이디 찾기\t").append("6.비밀번호 찾기\t").append("7.종료\n").
        append("============================================================================\n");
    printString(sb);
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

 // /** 로그인 전체 메뉴 */
/*  public void printLoginAllMenu() {
    index =1;
    sb.append("---로그인 전체 메뉴---\n").append("\t").
        append(index++).append(". 로그인\t").append(index++).append(". 아이디 찾기\t").append(index++).append(". 비밀번호 찾기\t").
        append(index++).append(". 회원등록\n").append("\t").append(index++).append(". 이전화면\n");
    printString(sb);
    printInputNumber();
  }*/

  /** 아이디가 중복시 출력문 */
  public void printDuplicateID() {
    sb.append("중복되는 아이디가 있습니다.\n");
    printString(sb);
  }

  /** 아이디가 없을시 출력문 */
  public void printNotFoundID() {
    sb.append("일치하는 아이디가 없습니다.\n");
    printString(sb);
  }

  /** 로그인 정보가 틀렸을떄 출력문 */
  public void printUnknownMember() {
    sb.append("아이디 혹은 비밀번호가 틀렸습니다.\n");
    printString(sb);
  }

  /** 메인 메뉴 */
  public void printMainMenu() {
    StringBuilder append = sb.append("---메인 메뉴---\n")
            .append("\t")
            .append("1.회원관리\t")
            .append("2.상품관리\t")
            .append("3.입고관리\t")
            .append("4.재고관리\t")
            .append("5.출고관리\t")
            .append("6.창고관리\t")
            .append("7.고객센터\t")
            .append("8.로그아웃\n");
    printString(sb);
    printInputNumber();
  }

  public void printInputUserID() {
    sb.append("사용자 아이디를 입력해 주세요. : ");
    printString(sb);
  }

  public void printInputPassWord() {
    sb.append("비밀번호를 입력해 주세요. : ");
    printString(sb);
  }
  public void printInputName() {
    sb.append("이름를 입력해 주세요. : ");
    printString(sb);
  }

  public void printInputPhoneNumber() {
    sb.append("전화번호를 입력해 주세요. : ");
    printString(sb);
  }

  public void printInputAddress() {
    sb.append("주소를 입력해 주세요. : ");
    printString(sb);
  }

  public void printInputEmail() {
    sb.append("이메일을 입력해 주세요. : ");
    printString(sb);
  }




  /** ID 찾기 매뉴 */
  public void printFindIDMenu() {
    index =1;
    sb.append("---아이디 찾기---\n").append("\t").append(index++).append(". 전화번호\t").append(index++).append(". 이메일").
        append(index++).append(". 사업자번호\n");
    printString(sb);
    printInputNumber();
  }
}
