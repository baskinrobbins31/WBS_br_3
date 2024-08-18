package ssg.dao.memberManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import ssg.dto.Member;
import ssg.enums.UserState;
import ssg.enums.UserType;
import ssg.library.dbio.AbstractDBIO;

public class memberManagementDao  extends AbstractDBIO {

  private StringBuilder sb = new StringBuilder();

  @Override
  protected Object read() {
    return super.read();
  }

  /** 전체 회원 조회 */
  public ArrayList<Member> readAll() {
    try {
        sb.append("SELECT * FROM member");
      String query = sb.toString();
      sb.delete(0, sb.length());
      ArrayList<Member> arrayListMember = new ArrayList<>();
      PreparedStatement ps = getConnection().prepareStatement(query);
      ResultSet rs = null;
      rs = ps.executeQuery();

      while (rs.next()) {
        arrayListMember.add(resultSetReturn(rs));
      }
      close(getConnection(), ps, rs);
      return arrayListMember;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<Member> readType(UserType type) {
    try {
      sb.append("SELECT * FROM member WHERE userType = '").append(type.toString()).append("'");
      String query = sb.toString();
      sb.delete(0, sb.length());
      ArrayList<Member> arrayListMember = new ArrayList<>();
      PreparedStatement ps = getConnection().prepareStatement(query);
      ResultSet rs = null;
      rs = ps.executeQuery();

      while (rs.next()) {
        arrayListMember.add(resultSetReturn(rs));
      }
      close(getConnection(), ps, rs);
      return arrayListMember;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /** resultSet 값 받기 */
  private Member resultSetReturn(ResultSet rs) {
    try {
      int id = rs.getInt("id");
      String userid = rs.getString("userid");
      String password = rs.getString("password");
      String name = rs.getString("userName");
      String BRN = rs.getString("BRN");
      UserType usertype = UserType.valueOf(rs.getString("userType"));
      UserState userState = UserState.valueOf(rs.getString("memberConfirm"));
      Timestamp timestamp = rs.getTimestamp("create_at");
      String phone = rs.getString("phone_number");
      String address = rs.getString("address");
      String email = rs.getString("email");
      return new Member(id, userid, password, name, BRN, usertype, userState, timestamp, phone, address, email);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  protected void update(Object o) {
    super.update(o);
  }

  @Override
  protected void delete(Object o) {
    super.delete(o);
  }


}
