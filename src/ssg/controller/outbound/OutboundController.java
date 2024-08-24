package ssg.controller.outbound;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import ssg.Main;
import ssg.dto.outbound.OutboundRequest;
import ssg.enums.OutboundState;
import ssg.enums.UserType;
import ssg.library.script.outboundscript.OutboundManagerScript;
import ssg.library.script.outboundscript.OutboundUserScript;
import ssg.library.script.warehousescript.WarehouseScript;
import ssg.service.outbound.OutboundService;

public class OutboundController {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private OutboundUserScript us = new OutboundUserScript();
  private OutboundManagerScript ms = new OutboundManagerScript();
  private OutboundService service = new OutboundService();

  /* 유저 권한 판별 */
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
        String menu = ms.printOutboundMenuMain();
        switch (menu) {
          case "1" -> {
            //1-1 출고 요청서 관리
            switch (ms.printOutMenu1()) {
              case 1 -> {processOutboundRequestManager();} //출고 요청서 승인/미승인 처리
              case 2 -> {} //출고 요청서 상태 수정
              case 3 -> {} //나가기
            }
          }
          case "2" -> {
            //1-2 출고 지시서 관리
          }
          case "3" -> {
            //1-3 출고 조회
          }
          default -> isOn = false;
        }
      } catch (IOException e) {
        System.out.println("입력 중 문제가 발생했습니다." + e.getMessage());
      }
    }
  }

  /** 출고관리(회원) */
  private void outboundMenuMember() {
    boolean isOn = true;
    while (isOn) {
      try {
        switch (us.printOutboundMenuMain()) {
          case 1 -> outboundRequest();
          case 2 -> {
            System.out.println("<출고 조회>");
            switch (us.printOutMenu3()) {
              case 1 -> {printOutboundReadAll();}
              case 2 -> {}
              case 3 -> {}
              default -> {}
            }
          }
          case 3 -> System.out.println("운송장 조회");
          default -> {isOn = false;}
        }
      } catch (IOException | NumberFormatException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  //출고 요청서 승인/미승인 처리하기
  private void processOutboundRequestManager() throws IOException {
    System.out.print("\n<출고 요청서 대기 처리>\n");
    service.getOutboundRequestListAll(OutboundState.OUTBOUND_WAIT); //대기 리스트 출력
    service.okOutboundRequest(ms.printOutMenu1_1(), OutboundState.OUTBOUND_OK);
    service.okOutboundRequest(ms.printOutMenu1_2(), OutboundState.OUTBOUND_DENIED);
  }

  /** 출고 요청 */
  private void outboundRequest() throws IOException {
    System.out.print("\n<출고 요청>\n");
    service.createOutboundRequest(createOutboundUserRequest());
  }

  private void printOutboundReadAll() throws IOException {
    System.out.print("\n<출고 지시서 전체 조회>\n");
    service.getOutboundListAll();
  }


  public OutboundRequest createOutboundUserRequest() throws IOException {
    int userid;
    int stockID;
    int locationID;
    int wssID;
    int outboundAmount;
    String deliveryAddress;
    String recipient;
    String recipientPhoneNumber;
    String outboundExplain;

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.print("/n<출고 요청>/n");
      userid = Main.loginOnMember.getId();
      System.out.print("재고 ID를 입력해주세요: ");
      stockID = Integer.parseInt(reader.readLine());
      WarehouseScript ws = new WarehouseScript();
      ws.registerWarehouseLocationId();
      locationID = Integer.parseInt(reader.readLine());
      System.out.print("창고 섹션 ID를 입력해주세요: ");
      wssID = Integer.parseInt(reader.readLine());
      System.out.print("출고 수량을 입력해주세요: ");
      outboundAmount = Integer.parseInt(reader.readLine());
      System.out.print("배송 주소를 입력해주세요: ");
      deliveryAddress = reader.readLine();
      System.out.print("수령인을 입력해주세요: ");
      recipient = reader.readLine();
      System.out.print("수령인 전화번호를 입력해주세요: ");
      recipientPhoneNumber = reader.readLine();
      System.out.print("출고 설명을 입력해주세요: ");
      outboundExplain = reader.readLine();
    }
    Timestamp createAt = new Timestamp(System.currentTimeMillis());

    return OutboundRequest.builder()
        .userid(userid)
        .stockID(stockID)
        .wssID(wssID)
        .outboundAmount(outboundAmount)
        .deliveryAddress(deliveryAddress)
        .locationID(locationID)
        .recipient(recipient)
        .recipientPhoneNumber(recipientPhoneNumber)
        .createAt(createAt)
        .outboundExplain(outboundExplain)
        .build();
  }

  /** 운송장 관리 */
  private void waybillMenuAdmin() {}

  /** 배차 관리 */
  private void vehicleAssignmentMenuAdmin() {}

}
