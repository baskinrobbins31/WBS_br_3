package ssg.dto.inbound;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssg.enums.InboundStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inbound {
  private int inboundId;
  private int productName;
  private int userId;
  private Integer wssId;
  private int subclassId;
  private Integer productAmount;
  private String qrId;
  private InboundStatus inboundStatus;
  private LocalDateTime inboundRequestDatetime;
  private LocalDateTime inboundApprovalDatetime;
  private LocalDateTime inboundExpectedDatetime;
}

