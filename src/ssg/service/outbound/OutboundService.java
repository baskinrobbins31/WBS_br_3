package ssg.service.outbound;

import java.util.Arrays;
import java.util.List;
import ssg.Main;
import ssg.dao.outbound.OutboundDao;
import ssg.dto.outbound.OutboundApproved;
import ssg.dto.outbound.OutboundRequest;
import ssg.enums.OutboundState;

public class OutboundService implements OutboundServiceInterface {

  private OutboundDao dao = new OutboundDao();

  @Override
  public void getOutboundRequestListAll(OutboundState state) { //미승인 요청지시서 리스트만 출력
    List<OutboundRequest> list = dao.readAll(state);
    StringBuilder sb = new StringBuilder();
    for (OutboundRequest o : list) {
      sb.append("-".repeat(50)).append("출고아이디\t재고아이디\t창고아이디\t출고수량\t배송지주소\t\t주소코드\t받는이\t연락처\t생성일\t출고요청상태\t기타").append("\n")
          .append(o.getOutboundID()).append("\t")
          .append(o.getUserid()).append("\t")
          .append(o.getStockID()).append("\t")
          .append(o.getWssID()).append("\t")
          .append(o.getOutboundAmount()).append("\t")
          .append(o.getDeliveryAddress()).append("\t")
          .append(o.getLocationID()).append("\t")
          .append(o.getRecipient()).append("\t")
          .append(o.getRecipientPhoneNumber()).append("\t")
          .append(o.getCreateAt()).append("\t")
          .append(o.getOutboundState()).append("\t")
          .append(o.getOutboundExplain()).append("\n")
          .append("-".repeat(50)).append("\n");
    }
    System.out.print(sb.toString());
  }

  @Override
  public void getOutboundListAll() {
    int id = Main.loginOnMember.getId();
    List<OutboundApproved> list = dao.readAll(id);
    for (OutboundApproved outbound : list) {
      System.out.print("출고아이디: " + outbound.getOutId());
      System.out.print("\t재고아이디: " + outbound.getStockId());
      System.out.print("\t창고아이디: " + outbound.getWssId());
      System.out.print("\t운송장아이디: " + outbound.getWaybillId());
      System.out.print("\t출고상태: " + outbound.getOutState());
      System.out.println("\t출고완료일시: " + outbound.getOutFinalTime());
      System.out.println("--------------------------------------------");
    }
  }

  /** 출고 요청 승인 */
  @Override
  public void okOutboundRequest(String ids, OutboundState state) {
    String[] split = ids.split(",");
    System.out.println(state.toString() + "처리를 시작합니다.");
    for (String s : split) {
      int id = Integer.parseInt(s.trim());
      dao.update(id, state);
    }
    System.out.println(state.toString() + "처리가 끝났습니다.");
  }

  /** create 출고 요청서  */
  @Override
  public void createOutboundRequest(OutboundRequest request) {
    dao.createOutboundRequest(request);
  }

}
