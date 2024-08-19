package ssg.controller.outbound;

import static ssg.Main.brInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.enums.OutboundState;
import ssg.enums.UserType;
import ssg.library.script.OutboundScript;
import ssg.service.outbound.OutboundService;

public class OutboundController {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private OutboundScript outboundScript = new OutboundScript();
  private OutboundService outboundService = new OutboundService();

  public void outboundMenuSelect(UserType type) {

    switch (type) {
      case ADMINISTRATOR, WH_ADMIN -> outboundMenuAdmin();
      case PRESIDENT_MEMBER, NORMAL_MEMBER -> outboundMenuMember();
    }
  }
  /** 출고관리 (관리자) */
  private void outboundMenuAdmin() {
    boolean isOn = true;
    while (isOn) {
      try {
        outboundScript.printOutboundMenuAdmin();
        int select = Integer.parseInt(brInstance.readLine());
        switch (select) {
          case 1 -> okOutboundRquest();
          case 2 -> outboundList(OutboundState.OUTBOUND_WAIT);
          case 3 -> System.out.println("출고상품 검색");
          case 4 -> outboundList(OutboundState.OUTBOUND_OK);
          case 5 -> System.out.println("출고리스트 검색");
          case 6 -> vehicleAssignmentMenuAdmin();
          case 7 -> waybillMenuAdmin();
          case 8 -> {System.out.println("이전화면");
          isOn = false;}
          default -> throw new NumberFormatException();
        }
      } catch (IOException | NumberFormatException e) {
        outboundScript.printFaultInput();
      }
    }
  }
  /** 출고관리(회원) */
  private void outboundMenuMember() {
    boolean isOn = true;
    while (isOn) {
      try {
        outboundScript.printOutboundMenuMember();
        int select = Integer.parseInt(brInstance.readLine());
        switch (select) {
          case 1 -> outboundRequest();
          case 2 -> outboundList(OutboundState.OUTBOUND_WAIT);
          case 3 -> System.out.println("출고상품 검색");
          case 4 -> outboundList(OutboundState.OUTBOUND_OK);
          case 5 -> System.out.println("출고리스트 검색");
          case 6 -> System.out.println("운송장 조회");
          case 7 -> {System.out.println("이전화면");
            isOn = false;}
          default -> throw new NumberFormatException();
        }
      } catch (IOException | NumberFormatException e) {
        outboundScript.printFaultInput();
      }
    }
  }


  /** 운송장 관리 */
  private void waybillMenuAdmin() {}

  /** 배차 관리 */
  private void vehicleAssignmentMenuAdmin() {}

  /** 출고 요청 */
  private void outboundRequest() {
    try {
      System.out.println("--출고 요청--");
      outboundScript.printInputID();
      int id = Integer.parseInt(brInstance.readLine());
      outboundService.outboundRequest();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 출고 요청 승인 */
  private void okOutboundRquest() {
    try {
      System.out.println("--출고요청 승인--");
      outboundScript.printInputID();
      int id = Integer.parseInt(brInstance.readLine());
      outboundService.okOutboundRequest(id);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 출고지시서, 리스트 조회 */
  private void outboundList(OutboundState state) {
    outboundService.outboundList(state);

  }

}
