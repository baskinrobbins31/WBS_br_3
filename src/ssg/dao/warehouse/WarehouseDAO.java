package ssg.dao.warehouse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ssg.dto.warehouse.SubWarehouse;
import ssg.dto.warehouse.Warehouse;
import ssg.library.dbio.AbstractDBIO2;

public class WarehouseDAO extends AbstractDBIO2 {
  //필드 선언 Connection, Statement, PreparedStatement, CallableStatement
  private Connection connection = null;
  private Statement stmt = null;
  private PreparedStatement psmt = null;
  private CallableStatement csmt = null;
  private ResultSet rs = null;

  //insert, update, delete
  public boolean executeQuery(String query) {
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        psmt.executeUpdate();
        return true;
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
    return false;
  }

  public boolean executeQuery(SubWarehouse sw) {
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        String query = "insert into sub_warehouse (ws_type, wss_number, ws_area_sqm, ws_w_sqm, ws_height, w_id) values (?,?,?,?,?,?);";
        psmt = connection.prepareStatement(query);
        psmt.setString(1, sw.getWsType());
        psmt.setInt(2, sw.getWssNumber());
        psmt.setFloat(3, sw.getWsAreaSqm());
        psmt.setFloat(4, sw.getWsWSqm());
        psmt.setFloat(5, sw.getWsHeight());
        psmt.setInt(6,sw.getWId());
        psmt.executeUpdate();
        return true;
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
    return false;
  }

  //read
  public List<Warehouse> readAll() {
    String query = "SELECT * FROM warehouse";
    List<Warehouse> warehouses = new ArrayList<>();
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        rs = psmt.executeQuery();
        while(rs.next()) {
          Warehouse warehouse = Warehouse.builder()
              .wId(rs.getInt(1))
              .wName(rs.getString(2))
              .location(rs.getString(3))
              .locationId(rs.getByte(4))
              .totalAreaSqm(rs.getFloat(5))
              .generalWSqm(rs.getFloat(6))
              .coldWSqm(rs.getFloat(7))
              .storageWSqm(rs.getFloat(8))
              .portWSqm(rs.getFloat(9))
              .bondedWSqm(rs.getFloat(10))
              .chemicalWSqm(rs.getFloat(11))
              .foodColdWSqm(rs.getFloat(12))
              .livestockWSqm(rs.getFloat(13))
              .marineColdWSqm(rs.getFloat(14))
              .relatedLaw(rs.getString(15))
              .handledItems(rs.getString(16))
              .manager(rs.getString(17))
              .regiDate(rs.getTimestamp(18))
              .employeesNumber(rs.getShort(19))
              .facilityEquipment(rs.getString(20))
              .contactNumber(rs.getString(21))
              .build();
          warehouses.add(warehouse);
        }
      }
      return warehouses;
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
    return warehouses;
  }

  public List<Warehouse> readAllLocationId (int locationId) {
    String query = "SELECT * FROM warehouse WHERE location_id = ?";
    List<Warehouse> warehouses = new ArrayList<>();
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        psmt.setInt(1, locationId);
        rs = psmt.executeQuery();
        while(rs.next()) {
          Warehouse warehouse = Warehouse.builder()
              .wId(rs.getInt(1))
              .wName(rs.getString(2))
              .location(rs.getString(3))
              .locationId(rs.getByte(4))
              .totalAreaSqm(rs.getFloat(5))
              .generalWSqm(rs.getFloat(6))
              .coldWSqm(rs.getFloat(7))
              .storageWSqm(rs.getFloat(8))
              .portWSqm(rs.getFloat(9))
              .bondedWSqm(rs.getFloat(10))
              .chemicalWSqm(rs.getFloat(11))
              .foodColdWSqm(rs.getFloat(12))
              .livestockWSqm(rs.getFloat(13))
              .marineColdWSqm(rs.getFloat(14))
              .relatedLaw(rs.getString(15))
              .handledItems(rs.getString(16))
              .manager(rs.getString(17))
              .regiDate(rs.getTimestamp(18))
              .employeesNumber(rs.getShort(19))
              .facilityEquipment(rs.getString(20))
              .contactNumber(rs.getString(21))
              .build();
          warehouses.add(warehouse);
        }
      }
      return warehouses;
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
    return warehouses;
  }

  public List<Warehouse> readAllLaw (String law) {
    String query = "SELECT * FROM warehouse WHERE related_law = ?";
    List<Warehouse> warehouses = new ArrayList<>();
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        psmt.setString(1, law);
        rs = psmt.executeQuery();
        while(rs.next()) {
          Warehouse warehouse = Warehouse.builder()
              .wId(rs.getInt(1))
              .wName(rs.getString(2))
              .location(rs.getString(3))
              .locationId(rs.getByte(4))
              .totalAreaSqm(rs.getFloat(5))
              .generalWSqm(rs.getFloat(6))
              .coldWSqm(rs.getFloat(7))
              .storageWSqm(rs.getFloat(8))
              .portWSqm(rs.getFloat(9))
              .bondedWSqm(rs.getFloat(10))
              .chemicalWSqm(rs.getFloat(11))
              .foodColdWSqm(rs.getFloat(12))
              .livestockWSqm(rs.getFloat(13))
              .marineColdWSqm(rs.getFloat(14))
              .relatedLaw(rs.getString(15))
              .handledItems(rs.getString(16))
              .manager(rs.getString(17))
              .regiDate(rs.getTimestamp(18))
              .employeesNumber(rs.getShort(19))
              .facilityEquipment(rs.getString(20))
              .contactNumber(rs.getString(21))
              .build();
          warehouses.add(warehouse);
        }
      }
      return warehouses;
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
    return warehouses;
  }

  public List<Warehouse> readAllName (String name) {
    String query = "{CALL warehouseProcName(?)}";
    List<Warehouse> warehouses = new ArrayList<>();
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        csmt = connection.prepareCall(query);
        csmt.setString(1, name);
        rs = csmt.executeQuery();
        while(rs.next()) {
          Warehouse warehouse = Warehouse.builder()
              .wId(rs.getInt(1))
              .wName(rs.getString(2))
              .location(rs.getString(3))
              .locationId(rs.getByte(4))
              .totalAreaSqm(rs.getFloat(5))
              .generalWSqm(rs.getFloat(6))
              .coldWSqm(rs.getFloat(7))
              .storageWSqm(rs.getFloat(8))
              .portWSqm(rs.getFloat(9))
              .bondedWSqm(rs.getFloat(10))
              .chemicalWSqm(rs.getFloat(11))
              .foodColdWSqm(rs.getFloat(12))
              .livestockWSqm(rs.getFloat(13))
              .marineColdWSqm(rs.getFloat(14))
              .relatedLaw(rs.getString(15))
              .handledItems(rs.getString(16))
              .manager(rs.getString(17))
              .regiDate(rs.getTimestamp(18))
              .employeesNumber(rs.getShort(19))
              .facilityEquipment(rs.getString(20))
              .contactNumber(rs.getString(21))
              .build();
          warehouses.add(warehouse);
        }
      }
      return warehouses;
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
    return warehouses;
  }

  public List<Warehouse> readAllLimit(int limit) {
    String query = "SELECT * FROM warehouse order by regi_date desc limit ?";
    List<Warehouse> warehouses = new ArrayList<>();
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        psmt.setInt(1, limit);
        rs = psmt.executeQuery();
        while(rs.next()) {
          Warehouse warehouse = Warehouse.builder()
              .wId(rs.getInt(1))
              .wName(rs.getString(2))
              .location(rs.getString(3))
              .locationId(rs.getByte(4))
              .totalAreaSqm(rs.getFloat(5))
              .generalWSqm(rs.getFloat(6))
              .coldWSqm(rs.getFloat(7))
              .storageWSqm(rs.getFloat(8))
              .portWSqm(rs.getFloat(9))
              .bondedWSqm(rs.getFloat(10))
              .chemicalWSqm(rs.getFloat(11))
              .foodColdWSqm(rs.getFloat(12))
              .livestockWSqm(rs.getFloat(13))
              .marineColdWSqm(rs.getFloat(14))
              .relatedLaw(rs.getString(15))
              .handledItems(rs.getString(16))
              .manager(rs.getString(17))
              .regiDate(rs.getTimestamp(18))
              .employeesNumber(rs.getShort(19))
              .facilityEquipment(rs.getString(20))
              .contactNumber(rs.getString(21))
              .build();
          warehouses.add(warehouse);
        }
      }
      return warehouses;
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
    return warehouses;
  }

  public ArrayList<Integer> readColumn(String column) {
    StringBuilder query = new StringBuilder();
    query.append("select ")
        .append(column)
        .append(" from warehouse");
    ArrayList<Integer> arrayList = new ArrayList<>();
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query.toString());
        rs = psmt.executeQuery();
        while(rs.next()) {
          arrayList.add(rs.getInt(1));
        }
      }
      return arrayList;
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
    return arrayList;
  }

  public List<SubWarehouse> readAllSubWarehouse(int limit) {
    String query = "SELECT * FROM sub_warehouse order by ws_id desc limit ?";
    List<SubWarehouse> subWarehouses = new ArrayList<>();
    try {
      //Class.forName("com.mysql.cj.jdbc.Driver");
      connection = super.getConnection();
      if (connection != null) {
        psmt = connection.prepareStatement(query);
        psmt.setInt(1, limit);
        rs = psmt.executeQuery();
        while(rs.next()) {
          SubWarehouse subWarehouse = SubWarehouse.builder()
              .wsId(rs.getInt(1))
              .wsType(rs.getString(2))
              .wssNumber(rs.getInt(3))
              .wsAreaSqm(rs.getFloat(4))
              .wsWSqm(rs.getFloat(5))
              .wsHeight(rs.getFloat(6))
              .wId(rs.getInt(7))
              .build();
          subWarehouses.add(subWarehouse);
        }
      }
      return subWarehouses;
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
    return subWarehouses;
  }

}
