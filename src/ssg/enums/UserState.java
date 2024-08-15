package ssg.enums;

public enum UserState {
  ACCESS_WAIT,    //회원 등록 대기
  ACCESS_OK,      //회원 등록 승인
  ACCESS_DENIED,  //회원 등록 거부
  ACCESS_LOCK     //회원 비활성(잠금)
}
