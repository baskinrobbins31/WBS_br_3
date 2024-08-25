package ssg.library.script.outboundscript;

import static ssg.Main.brInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.exception.ExceptionList;

public class OutboundManagerScript {

  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  ExceptionList el = new ExceptionList();


  /** 출고관리 메뉴 */
  public int printOutboundMenuMain() throws IOException {
    System.out.print("\n<출고 관리 메뉴>\n1. 출고 요청서 관리\t\t\t2. 출고 지시서 관리\t\t\t3. 출고 조회\t\t\t4. 이전화면\n\n메뉴 입력 : ");
    String menu = brInstance.readLine();
    el.throwIncorrectNumberRange(menu, 4);
    return Integer.parseInt(menu);
  }

  /** 출고 요청서 관리 */
  public int printOutMenu1() throws IOException {
    System.out.print("\n<출고 요청서 관리>\n1. 출고 요청서 대기 처리\t\t\t2. 출고 요청서 상태 수정\t\t\t3. 이전화면\n\n메뉴 입력 : ");
    String menu = brInstance.readLine();
    el.throwIncorrectNumberRange(menu, 3);
    return Integer.parseInt(menu);
  }

  public String printOutMenu1_1() throws IOException {
    System.out.print("\n승인하실 요청서의 번호를 입력해주세요 : ");
    return reader.readLine();
  }

  public String printOutMenu1_2() throws IOException {
    System.out.print("\n미승인하실 요청서의 번호를 입력해주세요 : ");
    return reader.readLine();
  }

/*
  public void printAdminVehicleAssignmentMenu() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n---배차 관리 메뉴---\n")
        .append("\t").append("1. 배차등록\t\t").append("2. 배차조회\t\t").append("3. 배차수정\t\t").append("4. 배차취소\n")
        .append("\t").append("5. 이전화면\n");
    sb.toString();
  }


  public void printAdminWaybillMenu() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n---운송장 관리 메뉴---\n").append("\t")
        .append("1.운송장 등록\t\t").append("2.운송장 조회\t\t").append("3.운송장 수정\t\t").append("4.운송장 검색\n")
        .append("\t").append("5.이전화면\n");
    sb.toString();
  }

  운송장 관리 메뉴(회원)
  public void printMemberWaybillMenu() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n---운송장 관리 메뉴---\n").append("\t")
        .append("1.운송장 조회\t").append("2.이전화면\n");
    sb.toString();
  }*/

}
