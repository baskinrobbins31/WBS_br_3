package ssg.dao.login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ssg.dto.Member;
import ssg.enums.UserState;
import ssg.enums.UserType;

import ssg.library.dbio.AbstractDBIO2;

public class LoginDao extends AbstractDBIO2 {

  private StringBuilder sb = new StringBuilder();

  @Override
  public void create(Object o) {

    if(o instanceof Member m) {
      sb.append("INSERT INTO  member(id, userid, password, userName, phone_number, address, email) VALUES ( '").
          append(m.getId()).append("', '").append(m.getUserid()).append("', '").append(m.getPassWord()).append("', '")
          .append(m.getUserName()).append("', '").append(m.getPhoneNumber()).append("', '").
          append(m.getAddress()).append("', '").append(m.getEmail()).append("' )");
      String qurey = sb.toString();

      try {
        //System.out.println(qurey);
        PreparedStatement ps = null;
        ps = getConnection().prepareStatement(qurey);
        ps.executeUpdate();
        commit();
        sb.delete(0, sb.length());

        close(getConnection(), ps);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    else {
      System.out.println("회원 등록 실패");
    }

  }


  /** 아이디, 비밀번호로 조회 로그인 할때 사용*/
  public Object read(String userid, String password) {
    try {
      sb.append("SELECT * FROM member WHERE userid = '").append(userid).append("' AND password = '").append(password).append("'");
      String query = sb.toString();//"SELECT * FROM member WHERE userid = '" + userid + "' AND password = '" + password + "'";
      ResultSet rs = null;
      PreparedStatement ps = null;
      ps = getConnection().prepareStatement(query);
      rs = ps.executeQuery();
      sb.delete(0, sb.length());

     /* String userID = userid;
      String userName = null;
      String BRN;
      UserType userType;
      UserState userState;
      Timestamp createAt;
      String phoneNumber;
      String address;
      String email;*/
      Member tempMember = null;
      while (rs.next()) {
/*        userName = rs.getString("userName");
        BRN = rs.getString("BRN");
        userType = UserType.valueOf(rs.getString("userType"));
        userState = UserState.valueOf(rs.getString("memberConfirm"));
        createAt = rs.getTimestamp("create_at");
        phoneNumber = rs.getString("phone_number");
        address = rs.getString("address");
        email = rs.getString("email");
        tempMember = new Member(userID, userName, BRN, userType,userState, createAt, phoneNumber, address, email);*/
        tempMember = Member.builder().id(rs.getInt("id")).userid(rs.getString("userid")).passWord(rs.getString("password")).userName(rs.getString("userName")).BRN(rs.getString("BRN"))
            .userType(UserType.valueOf(rs.getString("userType"))).memberConfirm(UserState.valueOf(rs.getString("memberConfirm")))
            .createAt( rs.getTimestamp("create_at")).phoneNumber(rs.getString("phone_number")).address(rs.getString("address"))
            .email(rs.getString("email")).build();
      }
      close(getConnection(), ps, rs);
        return tempMember;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }



  /** 비밀번호 찾기 에 사용 */
  public String read(String string, int select) {
    try {
      switch (select) {
        case 1 -> sb.append("SELECT userid FROM member WHERE phone_number = '").append(string).append("'");
        case 2 -> sb.append("SELECT userid FROM member WHERE email = '").append(string).append("'");
        case 3 -> sb.append("SELECT userid FROM member WHERE BRN = '").append(string).append("'");
        case 4 -> sb.append("SELECT password FROM member WHERE userid = '").append(string).append("'");
      }

      String query = sb.toString();
      ResultSet rs = null;
      PreparedStatement ps = null;
      ps = getConnection().prepareStatement(query);
      rs = ps.executeQuery();
      sb.delete(0, sb.length());

      String word = null;
      while (rs.next()) {
        if(select == 4)
          word = rs.getString("password");
        else if(select  == 1 || select == 2 || select == 3)
          word = rs.getString("userid");
      }
      close(getConnection(), ps, rs);
      return word;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }



  public void commit() {

    try {
      String query = "commit";
      PreparedStatement ps = null;
      ps = getConnection().prepareStatement(query);
      ps.executeUpdate();
      close(getConnection(), ps);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }



  /** 관리자ID 생성 (추후 삭제 필요) */
public void CreateAdmins() {

    sb.append("INSERT INTO  member(userid, password, userName, phone_number, address, email, userType, memberConfirm)").
        append("VALUES ( 'admin', 'admin123', '총관리자', '010-1234-9987', '서울시 강남구 SAC아트홀 6층', 'egurmaza@gmail.com', 'ADMINISTRATOR', 'ACCESS_OK')");
    String qurey = sb.toString();

    try {
      System.out.println(qurey);
      PreparedStatement ps = null;
      ps = getConnection().prepareStatement(qurey);
      ps.executeUpdate();
      commit();
      sb.delete(0, sb.length());

      close(getConnection(), ps);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

}


}
