package ssg.dao.inbound;

import ssg.dto.inbound.Inbound;
import ssg.dto.product.Product;
import ssg.dto.warehouse.Warehouse;
import ssg.enums.InboundStatus;
import ssg.library.dbio.AbstractDBIO2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InboundDAO extends AbstractDBIO2<Inbound> {

  @Override
  public void create(Inbound inbound) {
    String sql = "INSERT INTO inbound (product_name, user_id, wss_id, subclass_id, product_amount, qr_id, inbound_status, inbound_request_datetime, inbound_expected_datetime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(sql)) {
      pStmt.setString(1, inbound.getProductName());
      pStmt.setInt(2, inbound.getUserId());
      pStmt.setInt(3, inbound.getWssId());
      pStmt.setInt(4, inbound.getSubclassId());
      pStmt.setInt(5, inbound.getProductAmount());
      pStmt.setString(6, inbound.getQrId());
      pStmt.setString(7, inbound.getInboundStatus().getValue());
      pStmt.setTimestamp(8, Timestamp.valueOf(inbound.getInboundRequestDatetime()));
      pStmt.setTimestamp(9, Timestamp.valueOf(inbound.getInboundExpectedDatetime()));
      pStmt.executeUpdate();
    } catch (SQLException e) {
      System.err.println("입고 요청 생성 중 오류: " + e.getMessage());
    }
  }

  public Optional<Inbound> read(int id) {
    String sql = "SELECT * FROM inbound WHERE inbound_id = ?";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(sql)) {
      pStmt.setInt(1, id);
      try (ResultSet rs = pStmt.executeQuery()) {
        if (rs.next()) {
          Inbound inbound = mapRowToInbound(rs);
          return Optional.of(inbound);
        }
      }
    } catch (SQLException e) {
      System.err.println("입고 요청 조회 중 오류: " + e.getMessage());
    }
    return Optional.empty();
  }

  @Override
  public List<Inbound> readAll() {
    List<Inbound> inboundList = new ArrayList<>();
    String sql = "SELECT * FROM inbound i JOIN product p ON i.product_id = p.product_id";;
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery()) {
      while (rs.next()) {
        inboundList.add(mapRowToInbound(rs));
      }
    } catch (SQLException e) {
      System.err.println("입고 요청 목록 조회 중 오류: " + e.getMessage());
    }
    return inboundList;
  }

  @Override
  public void update(int id, Inbound inbound) {
    String sql = "UPDATE inbound SET product_name = ?, user_id = ?, wss_id = ?, subclass_id = ?, product_amount = ?, qr_id = ?, inbound_status = ?, inbound_approval_datetime = ?, inbound_expected_datetime = ? WHERE inbound_id = ?";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(sql)) {
      pStmt.setString(1, inbound.getProductName());
      pStmt.setInt(2, inbound.getUserId());
      pStmt.setInt(3, inbound.getWssId());
      pStmt.setInt(4, inbound.getSubclassId());
      pStmt.setInt(5, inbound.getProductAmount());
      pStmt.setString(6, inbound.getQrId());
      pStmt.setString(7, inbound.getInboundStatus().getValue());
      if (inbound.getInboundApprovalDatetime() != null) {
        pStmt.setTimestamp(8, Timestamp.valueOf(inbound.getInboundApprovalDatetime()));
      } else {
        pStmt.setNull(8, Types.TIMESTAMP);
      }
      pStmt.setTimestamp(9, Timestamp.valueOf(inbound.getInboundExpectedDatetime()));
      pStmt.setInt(10, id);
      pStmt.executeUpdate();
    } catch (SQLException e) {
      System.err.println("입고 요청 업데이트 중 오류: " + e.getMessage());
    }
  }

  @Override
  public void delete(int id) {
    String sql = "DELETE FROM inbound WHERE inbound_id = ?";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(sql)) {
      pStmt.setInt(1, id);
      pStmt.executeUpdate();
    } catch (SQLException e) {
      System.err.println("입고 요청 삭제 중 오류: " + e.getMessage());
    }
  }


  public List<Product> findProductsByName(String keyword) throws SQLException {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM product WHERE product_name LIKE ?";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(sql)) {
      pStmt.setString(1, "%" + keyword + "%");
      ResultSet rs = pStmt.executeQuery();
      while (rs.next()) {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        products.add(product);
      }
    }
    return products;
  }

  public List<Warehouse> findWarehousesByMemberId(int memberId) {
    List<Warehouse> warehouses = new ArrayList<>();
    String sql = "SELECT * FROM warehouses WHERE member_id = ?";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(sql)) {
      pStmt.setInt(1, memberId);
      try (ResultSet rs = pStmt.executeQuery()) {
        while (rs.next()) {
          Warehouse warehouse = new Warehouse();
          warehouse.setWId(rs.getInt("w_id"));
          warehouse.setWName(rs.getString("w_name"));
          // 다른 필요한 필드도 설정
          warehouses.add(warehouse);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return warehouses;
  }

  private Inbound mapRowToInbound(ResultSet rs) throws SQLException {
    return new Inbound(
        rs.getInt("inbound_id"),
        rs.getInt("product_id"),
        rs.getString("product_name"),
        rs.getInt("user_id"),
        rs.getInt("wss_id"),
        rs.getInt("subclass_id"),
        rs.getInt("product_amount"),
        rs.getString("qr_id"),
        InboundStatus.valueOf(rs.getString("inbound_status")),
        rs.getTimestamp("inbound_request_datetime").toLocalDateTime(),
        rs.getTimestamp("inbound_approval_datetime") != null ? rs.getTimestamp("inbound_approval_datetime").toLocalDateTime() : null,
        rs.getTimestamp("inbound_expected_datetime").toLocalDateTime()
    );
  }
}
