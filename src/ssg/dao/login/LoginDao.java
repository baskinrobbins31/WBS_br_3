package ssg.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import ssg.dto.Member;
import ssg.enums.UserType;
import ssg.library.dbio.AbstractDBIO;

public class LoginDao extends AbstractDBIO {

  private StringBuilder sb = new StringBuilder();

  @Override
  public void create(Object o) {

    if(o instanceof Member m) {
      sb.append("INSERT INTO  member(id, userid, password, userName, phone_number, address, email) VALUES ( '").
          append(m.getId()).append("', '").append(m.getUserid()).append("', '").append(m.getPassWord()).append("', '")
          .append(m.getUserName()).append("', '").append(m.getPhoneNumber()).append("', '").
          append(m.getAddress()).append("', '").append(m.getEmail()).append("' )");
      String qurey = sb.toString();
      System.out.println(qurey);
      super.excute(qurey);
      commit();
      sb.delete(0, sb.length());
    }
    else {
      System.out.println("회원 등록 실패");
    }

  }

  @Override
  public Object read() {
    return null;
  }

  /** 아이디, 비밀번호로 조회 로그인 할때 사용*/
  public Object read(String userid, String password) {
    try {
      sb.append("SELECT * FROM member WHERE userid = '").append(userid).append("' AND password = '").append(password).append("'");
      String query = sb.toString();//"SELECT * FROM member WHERE userid = '" + userid + "' AND password = '" + password + "'";
      ResultSet rs = null;
      rs = super.excute(query, rs);
      sb.delete(0, sb.length());

      String userID = userid;
      String userName = null;
      String BRN;
      UserType userType;
      Timestamp createAt;
      String phoneNumber;
      String address;
      String email;
      Member tempMember = null;
      while (rs.next()) {
        userName = rs.getString("userName");
        BRN = rs.getString("BRN");
        userType = UserType.valueOf(rs.getString("userType"));
        createAt = rs.getTimestamp("create_at");
        phoneNumber = rs.getString("phone_number");
        address = rs.getString("address");
        email = rs.getString("email");
        tempMember = new Member(userID, userName, BRN, userType, createAt, phoneNumber, address, email);
      }
        rs.close();
        close();
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
      rs = super.excute(query, rs);
      sb.delete(0, sb.length());

      String word = null;
      while (rs.next()) {
        if(select == 4)
          word = rs.getString("password");
        else if(select  == 1 || select == 2 || select == 3)
          word = rs.getString("userid");
      }
      rs.close();
      close();
      return word;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }



  @Override
  public void update(Object o) {

  }

  @Override
  public void delete(Object o) {

  }

  public void commit() {
    String query = "commit";
    super.excute(query);
  }
}
