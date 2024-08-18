package ssg.dao.inbound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ssg.dto.Product;
import ssg.library.dbio.AbstractDBIO;

public class ProductDAO extends AbstractDBIO {

  // 제품 이름으로 제품을 찾는 메서드
  public Optional<Product> findProductByName(String productName) {
    String query = "SELECT * FROM product WHERE product_name = ?";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(query)) {

      pStmt.setString(1, productName);
      try (ResultSet rs = pStmt.executeQuery()) {
        if (rs.next()) {
          return Optional.of(new Product(
              rs.getInt("product_id"),
              rs.getString("product_name"),
              rs.getInt("product_price"),
              rs.getString("product_info"),
              rs.getInt("subclass_id")
          ));
        }
      }
    } catch (SQLException e) {
      System.err.println("오류가 발생하였습니다: " + e.getMessage());
    }
    return Optional.empty();
  }

  // 제품 이름으로 유사한 제품들을 찾는 메서드
  public List<Product> findProductsByName(String productName) {
    List<Product> products = new ArrayList<>();
    String query = "SELECT * FROM product WHERE product_name LIKE ?";
    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(query)) {

      pStmt.setString(1, "%" + productName + "%");
      try (ResultSet rs = pStmt.executeQuery()) {
        while (rs.next()) {
          products.add(new Product(
              rs.getInt("product_id"),
              rs.getString("product_name"),
              rs.getInt("product_price"),
              rs.getString("product_info"),
              rs.getInt("subclass_id")
          ));
        }
      }
    } catch (SQLException e) {
      System.err.println(e);
    }
    return products;
  }

  @Override
  public void create(Object o) {
    if (o instanceof Product product) {
      String query = "INSERT INTO product (product_name, product_price, product_info, subclass_id) VALUES (?, ?, ?, ?)";
      try (Connection connection = getConnection();
          PreparedStatement pStmt = connection.prepareStatement(query)) {

        pStmt.setString(1, product.getProductName());
        pStmt.setInt(2, product.getProductPrice());
        pStmt.setString(3, product.getProductInfo());
        pStmt.setInt(4, product.getSubclassId());
        pStmt.executeUpdate();
      } catch (SQLException e) {
        System.err.println(e); // 추후 logger, optional 고려
      }
    } else {
      System.err.println("옳지 않은 객체");
    }
  }

  public List<Product> read() {
    List<Product> products = new ArrayList<>();
    String query = "SELECT * FROM product";

    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(query);
        ResultSet rs = pStmt.executeQuery()) {

      while (rs.next()) {
        products.add(new Product(
            rs.getInt("product_id"),
            rs.getString("product_name"),
            rs.getInt("product_price"),
            rs.getString("product_info"),
            rs.getInt("subclass_id")
        ));
      }
    } catch (SQLException e) {
      System.err.println(e);
    }

    return products;
  }

  @Override
  public void update(Object o) {
    if (o instanceof Product product) {
      String query = "UPDATE product SET product_name = ?, product_price = ?, product_info = ? WHERE product_name = ?";
      try (Connection connection = getConnection();
          PreparedStatement pStmt = connection.prepareStatement(query)) {

        pStmt.setString(1, product.getProductName());
        pStmt.setInt(2, product.getProductPrice());
        pStmt.setString(3, product.getProductInfo());
        pStmt.executeUpdate();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }

  @Override
  public void delete(Object o) {
    if (o instanceof String productName) {
      String query = "DELETE FROM product WHERE product_name = ?";
      try (Connection connection = getConnection();
          PreparedStatement pStmt = connection.prepareStatement(query)) {

        pStmt.setString(1, productName);
        pStmt.executeUpdate();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }
}