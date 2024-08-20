package ssg.dto.outbound;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ssg.enums.OutboundState;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OutboundRequest {
  private int outboundID;
  private int userid;
  private int stockID;
  private int wssID;
  private int outboundAmount;
  private String deliveryAddress;
  private int locationID;
  private String recipient;
  private String recipientPhoneNumber;
  private Timestamp createAt;
  private OutboundState outboundState;  //출고 상태
  private String outboundExplain;
}
