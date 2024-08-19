package ssg.dto;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import ssg.enums.OutboundState;

@Getter
public class Outbound {
  private int outboundID;   //출고번호
  private int userid;
  private int stockID;
  private int wssID;
  private int outboundAmount =0;
  private String deliveryAddress;
  private int locationID;
  private String recipient;
  private String recipientPhoneNumber;
  private Timestamp createAt;
  private OutboundState outboundState = OutboundState.OUTBOUND_WAIT;  //출고 상태
  private String outboundExplain;

  @Builder
  public Outbound(int outboundID, int userid, int stockID, int wssID, int outboundAmount,
      String deliveryAddress, int locationID, String recipient, String recipientPhoneNumber,
      Timestamp createAt, OutboundState outboundState, String outboundExplain) {
    this.outboundID = outboundID;
    this.userid = userid;
    this.stockID = stockID;
    this.wssID = wssID;
    this.outboundAmount = outboundAmount;
    this.deliveryAddress = deliveryAddress;
    this.locationID = locationID;
    this.recipient = recipient;
    this.recipientPhoneNumber = recipientPhoneNumber;
    this.createAt = createAt;
    this.outboundState = outboundState;
    this.outboundExplain = outboundExplain;
  }

}
