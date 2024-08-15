package ssg.dto;

import java.sql.Timestamp;
import ssg.enums.UserState;
import ssg.enums.UserType;

public class Member {
  private int id;   //id
  private String userid;    // 사용자 로그인 id
  private String passWord;  //사용자 비밀번호
  private String userName;  //사용자 이름
  private String BRN;       //사업자번호
  private UserType userType; //사용자 권한
  private UserState memberConfirm;//회원등록 상태
  private Timestamp createAt;   //생성일
  private String phone_number;  //회원 전화번호
  private String address;       //회원 주소
  private String email;         //회원 e메일
}
