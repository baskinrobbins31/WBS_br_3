package ssg.dao.memberManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ssg.dto.Member;
import ssg.enums.UserState;
import ssg.enums.UserType;
import ssg.library.dbio.AbstractDBIO;

public class memberManagementDao  extends AbstractDBIO {

  private StringBuilder sb = new StringBuilder();



  @Override
  /** 전체 회원 조회 */
  public List<Member> read() {
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

  public List<Member> readType(UserType type) {
    try {
      sb.append("SELECT * FROM member WHERE userType = '").append(type.toString()).append("'");
      String query = sb.toString();
      sb.delete(0, sb.length());
      List<Member> ListMember = new ArrayList<>();
      PreparedStatement ps = getConnection().prepareStatement(query);
      ResultSet rs = null;
      rs = ps.executeQuery();

      while (rs.next()) {
        ListMember.add(resultSetReturn(rs));
      }
      close(getConnection(), ps, rs);
      return ListMember;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /** resultSet 값 받기 */
  private Member resultSetReturn(ResultSet rs) {
    try {
      return Member.builder().id(rs.getInt("id")).userid(rs.getString("userid")).passWord(rs.getString("password"))
          .userName(rs.getString("userName")).BRN(rs.getString("BRN")).userType(UserType.valueOf(rs.getString("userType")))
          .memberConfirm(UserState.valueOf(rs.getString("memberConfirm"))).createAt(rs.getTimestamp("create_at"))
          .phoneNumber(rs.getString("phone_number")).address(rs.getString("address")).email(rs.getString("email")).build();
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
