package ssg.library.script;

public class LoginScript {


  StringBuilder sb =new StringBuilder();
  int index = 1;

  private static LoginScript loginScriptInstance = new LoginScript();

  public static LoginScript getLoginScriptInstance() {
    return loginScriptInstance;
  }

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
        append(index++).append(". 종료\n").
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
    sb.append("---로그인 전체 메뉴---\n").append("\t").
        append(index++).append(". 로그인\t").append(index++).append(". 아이디 찾기\t").append(index++).append(". 비밀번호 찾기\t").
        append(index++).append(". 회원등록\t").append("\t").append(index++).append(". 이전화면\n");
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

}
