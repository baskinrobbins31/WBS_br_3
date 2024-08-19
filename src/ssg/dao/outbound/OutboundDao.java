package ssg.dao.outbound;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ssg.dto.Member;
import ssg.dto.Outbound;
import ssg.enums.OutboundState;
import ssg.enums.UserState;
import ssg.enums.UserType;
import ssg.library.dbio.AbstractDBIO2;

public class OutboundDao extends AbstractDBIO2 {

  private StringBuilder sb = new StringBuilder();

  @Override
  protected void create(Object o) {
    super.create(o);
  }

  @Override
  protected Object read() {
    return super.read();
  }


  /** 출고 요청서 모두 조회 */
  @Override
  protected List<Outbound> readAll() {
    try {
      List<Outbound> outboundList = new ArrayList<>();
      sb.append("SELECT * FROM outbound");
      String query = sb.toString();
      PreparedStatement ps = getConnection().prepareStatement(query);
      ResultSet rs = null;
      rs = ps.executeQuery();

      while (rs.next()) {
        outboundList.add(resultSetReturn(rs));
      }
      close(getConnection(), ps, rs);
      return outboundList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }



  /** 출고 지시서, 출고요청서 조회 */
  public List<Outbound> read(OutboundState state) {
    try {
      List<Outbound> outboundList = new ArrayList<>();
      sb.append("SELECT * FROM outbound WHERE OutboundState = '").append(state.toString()).append("'");
      String query = sb.toString();
      PreparedStatement ps = getConnection().prepareStatement(query);
      ResultSet rs = null;
      rs = ps.executeQuery();

      while (rs.next()) {
        outboundList.add(resultSetReturn(rs));
      }
      close(getConnection(), ps, rs);
      return outboundList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  protected void update(int id, Object o) {
    super.update(id, o);
  }

  @Override
  protected void delete(int id) {
    super.delete(id);
  }

  /** resultSet 값 받기 */
  private Outbound resultSetReturn(ResultSet rs) {
//  try {
    return Outbound.builder().build();
//  } catch (SQLException e) {
//    throw new RuntimeException(e);
//  }
  }
}




