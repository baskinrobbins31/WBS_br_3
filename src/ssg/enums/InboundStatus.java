package ssg.enums;
//import lombok.Getter;

import lombok.Data;
import lombok.Getter;

//@Getter
@Getter
public enum InboundStatus {

  PENDING_REQUEST("입고요청 대기"),
  ADJUSTING_REQUEST("입고요청 수정"),
  REJECTED_REQUEST("입고요청 거절"),
  APPROVED_REQUEST("입고대기"),
  COMPLETED_INBOUND("입고완료");

  InboundStatus(String value) {
    this.value = value;
  }

  private final String value;
}
