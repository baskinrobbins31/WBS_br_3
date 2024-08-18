package ssg.dto;

import java.sql.Timestamp;
import lombok.Getter;
import ssg.enums.UserState;
import ssg.enums.UserType;

@Getter
public class Member {
  private int id;   //id
  private String userid;    // 사용자 로그인 id
  private String passWord;  //사용자 비밀번호
  private String userName;  //사용자 이름
  private String BRN;       //사업자번호
  private UserType userType; //사용자 권한
  private UserState memberConfirm;//회원등록 상태
  private Timestamp createAt;   //생성일
  private String phoneNumber;  //회원 전화번호
  private String address;       //회원 주소
  private String email;         //회원 e메일

  Member() {}

  /** 회원 등록 할때 사용 */
  public Member(int id, String userid, String passWord, String userName, String phoneNumber,
      String address, String email) {
    this.id = id;
    this.userid = userid;
    this.passWord = passWord;
    this.userName = userName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.email = email;
  }


  public Member(String userid, String userName, String BRN, UserType userType, UserState userState,
       Timestamp createAt, String phoneNumber, String address,
      String email) {
    this.userid = userid;
    this.userName = userName;
    this.BRN = BRN;
    this.userType = userType;
    this.memberConfirm = userState;
    this.createAt = createAt;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.email = email;
  }

  public Member(int id, String userid, String passWord, String userName, String BRN,
      UserType userType, UserState memberConfirm, Timestamp createAt, String phoneNumber,
      String address, String email) {
    this.id = id;
    this.userid = userid;
    this.passWord = passWord;
    this.userName = userName;
    this.BRN = BRN;
    this.userType = userType;
    this.memberConfirm = memberConfirm;
    this.createAt = createAt;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.email = email;
  }
}
