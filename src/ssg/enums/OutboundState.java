package ssg.enums;

public enum OutboundState {
  OUTBOUND_WAIT,    //출고요청 대기
  OUTBOUND_OK,      //출고요청 승인
  OUTBOUND_DENIED,  //출고요청 거부
  OUTBOUND_CANCEL,  //출고요청 취소
  OUTBOUND_ETC      //출고요청 기타

}
