package ssg.dao.memberManagement;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ssg.dto.Member;
import ssg.enums.UserState;
import ssg.enums.UserType;
import ssg.library.dbio.AbstractDBIO2;

public class MemberManagementDao extends AbstractDBIO2 {

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

  public List<Member> readBRN(String brn) {
    try {
      sb.append("SELECT * FROM member WHERE BRN = '").append(brn).append("'");
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

  public List<Member> readConfirmMember() {
    try {
      sb.append("SELECT * FROM member WHERE memberConfirm = '").append("ACCESS_WAIT").append("'");
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



  public int update(Object o) {
    if(o instanceof Member m) {
      try {
        sb.append("UPDATE member SET ").append("password = '").append(m.getPassWord()).append("', ")
            .append("userName = '").append(m.getUserName()).append("', ")
            .append("BRN = '").append(m.getBRN()).append("', ")
            .append("phone_number = '").append(m.getPhoneNumber()).append("', ")
            .append("address = '").append(m.getAddress()).append("', ")
            .append("email = '").append(m.getEmail()).append("' ")
            .append("WHERE id = ").append(m.getId());

        String query = sb.toString();
        sb.delete(0, sb.length());
        PreparedStatement ps = getConnection().prepareStatement(query);
        int updaterow = ps.executeUpdate();
        close(getConnection(), ps);
        commit();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }


  public void update(int id) {
    try {
      sb.append("UPDATE member SET memberConfirm = ").append("'ACCESS_OK'").append(" WHERE id = '").append(id).append("'");
      String query = sb.toString();
      sb.delete(0, sb.length());
      PreparedStatement ps = getConnection().prepareStatement(query);
      ps.executeUpdate();
      close(getConnection(),ps);
      commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateAll() {
    try {
      sb.append("UPDATE member SET memberConfirm = ").append("'ACCESS_OK'").append(" WHERE memberConfirm = '").append("ACCESS_WAIT").append("'");
      String query = sb.toString();
      sb.delete(0, sb.length());
      PreparedStatement ps = getConnection().prepareStatement(query);
      ps.executeUpdate();
      close(getConnection(),ps);
      commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateAuthority(UserType type, int id) {
    try {
      sb.append("UPDATE member SET userType = '").append(type.toString()).append("' WHERE id = ").append(id);
      String query = sb.toString();
      sb.delete(0, sb.length());
      PreparedStatement ps = getConnection().prepareStatement(query);
      ps.executeUpdate();
      close(getConnection(),ps);
      commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  public void delete(int id, String BRN) {
    try {
      if(BRN.equals("null"))
        sb.append("DELETE FROM member WHERE id = ").append(id);
      else
        sb.append("DELETE FROM member WHERE id = ").append(id).append(" AND BRN = '").append(BRN).append("'");
      String query = sb.toString();
      sb.delete(0, sb.length());
      PreparedStatement ps = getConnection().prepareStatement(query);
      ps.executeUpdate();
      close(getConnection(),ps);
      commit();
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

}
