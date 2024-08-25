package ssg.library.script.outboundscript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.enums.errorcode.ErrorCode;
import ssg.exception.Exception;
import ssg.exception.ExceptionList;
import ssg.library.script.Script;

public class OutboundUserScript extends Script {

  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  /** 출고관리 메뉴(회원) */
  public int printOutboundMenuMain() throws IOException {
    System.out.print("\n<출고 관리 메뉴>\n1. 출고 요청\t\t\t2. 출고 조회\t\t\t3. 운송장 조회\t\t\t4. 이전화면\n\n메뉴 입력 : ");
    String menu = reader.readLine();
    if (ExceptionList.isNumberInRange(menu,4)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return Integer.parseInt(menu);
  }

  /** 출고 조회 */
  public int printOutMenu3() throws IOException {
    System.out.println("<출고 조회>\n1. 출고 지시서 전체 조회\t\t\t2. 출고 완료 리스트 조회\t\t\t3. 출고 상품 조회\t\t\t4.이전화면\n\n메뉴 입력 : ");
    String menu = reader.readLine();
    if (ExceptionList.isNumberInRange(menu,4)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return Integer.parseInt(menu);
  }

}
