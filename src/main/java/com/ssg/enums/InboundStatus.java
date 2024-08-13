package main.java.com.ssg.enums;
import lombok.Getter;

@Getter
public enum InboundStatus {

  FENDING_REQUEST("입고요청 대기"),
  APPROVED_REQUEST("입고요청 수정"),
  COMPLETED_REQUEST("입고요청 완료"),
  REJECTED_REQUEST("입고요청 거절");

  InboundStatus(String value) {
    this.value = value;
  }

  private final String value;
}
