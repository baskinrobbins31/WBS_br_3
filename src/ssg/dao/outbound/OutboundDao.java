package ssg.dao.outbound;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ssg.Main;
import ssg.dto.outbound.Outbound;
import ssg.dto.outbound.OutboundApproved;
import ssg.dto.outbound.OutboundRequest;
import ssg.enums.OutboundState;
import ssg.library.dbio.AbstractDBIO2;

public class OutboundDao extends AbstractDBIO2 {

  private StringBuilder sb = new StringBuilder();
  private Connection connection = null;
  private PreparedStatement psmt = null;
  private final CallableStatement csmt = null;
  private ResultSet rs = null;

  //create
  public void createOutboundRequest(OutboundRequest outboundRequest) {
    try {
      connection = super.getConnection();
      if (connection != null) {
        String query = "INSERT INTO outbound_request "
            + "(user_id, stock_id, wss_id, out_amount, delivery_address, "
            + "location_id, recipient, recipient_number, created_at, outbound_explain) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        psmt.setInt(1, outboundRequest.getUserid()); // user_id
        psmt.setInt(2, outboundRequest.getStockID()); // stock_id
        psmt.setInt(3, outboundRequest.getWssID()); // wss_id
        psmt.setInt(4, outboundRequest.getOutboundAmount()); // out_amount
        psmt.setString(5, outboundRequest.getDeliveryAddress()); // delivery_address
        psmt.setInt(6, outboundRequest.getLocationID()); // location_id
        psmt.setString(7, outboundRequest.getRecipient()); // recipient
        psmt.setString(8, outboundRequest.getRecipientPhoneNumber()); // recipient_number
        psmt.setTimestamp(9, outboundRequest.getCreateAt()); // created_at
        psmt.setString(10, outboundRequest.getOutboundExplain()); // outbound_explain
        psmt.executeUpdate();
      }
    } catch (NullPointerException e) {
      System.err.println(e.getMessage() + "DB 커넥션이 실패했습니다");
    } catch (SQLException e) {
      System.err.println(e.getMessage() + "DB에서 쿼리를 수행하던 중 문제가 발생했습니다");
    } catch (RuntimeException e) {
      System.err.println(e.getMessage());
    } finally {
      if (psmt != null) {
        try {
          psmt.close();
        } catch (SQLException e) {
          System.err.println("PreparedStatement를 닫는 중 오류가 발생했습니다: " + e.getMessage());
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          System.err.println("DB 연결을 닫는 중 오류가 발생했습니다: " + e.getMessage());
        }
      }
    }
  }

  //read
  public List<OutboundRequest> readAll(OutboundState state) {
    String query = "SELECT * FROM outbound_request where user_id = ? and out_request_state = ?";
    List<OutboundRequest> requests = new ArrayList<>();
    try {
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        psmt.setInt(1, Main.loginOnMember.getId());
        psmt.setString(2, String.valueOf(state));
        rs = psmt.executeQuery();
        while (rs.next()) {
          requests.add(resultSetReturnOutboundRequest(rs));
        }
      }
      return requests;
    } catch (NullPointerException e) {
      System.err.println(e.getMessage() + "DB 커넥션이 실패했습니다");
    } catch (SQLException e) {
      System.err.println(e.getMessage() + "DB에서 쿼리를 수행하던 중 문제가 발생했습니다");
    } catch (RuntimeException e) {
      System.err.println(e.getMessage());
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          System.err.println("ResultSet을 닫는 중 오류 발생: " + e.getMessage());
        }
      }
      if (psmt != null) {
        try {
          psmt.close();
        } catch (SQLException e) {
          System.err.println("PreparedStatement를 닫는 중 오류가 발생했습니다: " + e.getMessage());
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          System.err.println("DB 연결을 닫는 중 오류가 발생했습니다: " + e.getMessage());
        }
      }
    }
    return requests;
  }

  /* 출고 요청서 모두 조회 */
  public List<OutboundRequest> readAllRequest() {
    try {
      List<OutboundRequest> outboundList = new ArrayList<>();
      PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM outbound_request");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        outboundList.add(resultSetReturnOutboundRequest(rs));
      }
      close(getConnection(), ps, rs);
      return outboundList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /* 출고 요청서 상태에 따른 조회 */
  public List<OutboundRequest> read(OutboundState state) {
    try {
      List<OutboundRequest> outboundList = new ArrayList<>();
      sb.append("SELECT * FROM outbound_request WHERE out_request_state = '").append(state.toString()).append("'");
      PreparedStatement ps = getConnection().prepareStatement(sb.toString());
      sb.delete(0, sb.length());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        outboundList.add(resultSetReturnOutboundRequest(rs));
      }
      close(getConnection(), ps, rs);
      return outboundList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /* 출고 지시서 모두 조회 */
  public List<OutboundApproved> readAll(int id) {
    try {
      PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM outbound where user_id = ?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      List<OutboundApproved> outboundList = new ArrayList<>();
      while (rs.next()) {
        outboundList.add(resultSetReturnOutboundApproved(rs));
      }
      close(getConnection(), ps, rs);
      return outboundList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /* 출고요청서 상태에 따른 조회 : 대기상태, 미승인상태,승인상태 등등 */
  public int update(int id, OutboundState state){
    try {
      PreparedStatement ps = getConnection().prepareStatement("UPDATE outbound_request SET out_request_state = ? WHERE out_id = ?");
      ps.setString(1, state.toString());
      ps.setInt(2, id);
      int updaterow = ps.executeUpdate();
      close(getConnection(), ps);
      return updaterow;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /** resultSet 값 받기 */
  private Outbound resultSetReturn(ResultSet rs) {
    try {
      return Outbound.builder().outboundID(rs.getInt("out_id")).userid(rs.getInt("user_id")).stockID(rs.getInt("stock_id"))
          .wssID(rs.getInt("wss_id")).outboundAmount(rs.getInt("out_amount")).deliveryAddress(rs.getString("delivery_address"))
          .locationID(rs.getInt("location_id")).recipient(rs.getString("recipient")).recipientPhoneNumber(rs.getString("recipient_number"))
          .createAt(rs.getTimestamp("created_at")).outboundState(OutboundState.valueOf(rs.getString("out_request_state")))
          .outboundExplain(rs.getString("out_explain")).build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /** resultSet 값 받기 */
  private OutboundRequest resultSetReturnOutboundRequest(ResultSet rs) {
    try {
      return OutboundRequest.builder().outboundID(rs.getInt("out_id")).userid(rs.getInt("user_id")).stockID(rs.getInt("stock_id"))
          .wssID(rs.getInt("wss_id")).outboundAmount(rs.getInt("out_amount")).deliveryAddress(rs.getString("delivery_address"))
          .locationID(rs.getInt("location_id")).recipient(rs.getString("recipient")).recipientPhoneNumber(rs.getString("recipient_number"))
          .createAt(rs.getTimestamp("created_at")).outboundState(OutboundState.valueOf(rs.getString("out_request_state")))
          .outboundExplain(rs.getString("out_explain")).build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /** resultSet 값 받기 */
  private OutboundApproved resultSetReturnOutboundApproved(ResultSet rs) {
    try {
      return OutboundApproved.builder()
          .outId(rs.getInt("out_id"))
          .userId(rs.getInt("user_id"))
          .stockId(rs.getInt("stock_id"))
          .wssId(rs.getInt("wss_id"))
          .waybillId(rs.getObject("waybill_id") != null ? rs.getInt("waybill_id") : null)  // Nullable
          .outState(rs.getByte("out_state"))
          .outFinalTime(rs.getTimestamp("out_final_time"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}




