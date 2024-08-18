package ssg.dao.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ssg.dto.category.MajorCategory;
import ssg.dto.category.MiddleCategory;
import ssg.dto.category.SubclassCategory;
import ssg.library.dbio.AbstractDBIO;

public class CategoryDAO extends AbstractDBIO {

  public List<MajorCategory> getMajorCategory() throws SQLException {
    List<MajorCategory> majorCategory = new ArrayList<>();
    String query = "SELECT * FROM major_category";

    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(query);
        ResultSet rs = pStmt.executeQuery()) {

      while (rs.next()) {
        MajorCategory category = new MajorCategory(
            rs.getInt("major_id"),
            rs.getString("major_name")
        );
        majorCategory.add(category);
      }
    }
    return majorCategory;
  }

  public List<MiddleCategory> getMiddleCategory(int majorId) throws SQLException {
    List<MiddleCategory> middleCategory = new ArrayList<>();
    String query = "SELECT * FROM middle_category WHERE major_id = ?";

    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(query)) {

      pStmt.setInt(1, majorId);
      try (ResultSet rs = pStmt.executeQuery()) {
        while (rs.next()) {
          MiddleCategory category = new MiddleCategory(
              rs.getInt("middle_id"),
              rs.getString("middle_name"),
              rs.getInt("major_id")
          );
          middleCategory.add(category);
        }
      }
    }
    return middleCategory;
  }

  public List<SubclassCategory> getSubclassCategory(int middleId) throws SQLException {
    List<SubclassCategory> subclassCategory = new ArrayList<>();
    String query = "SELECT * FROM subclass_category WHERE middle_id = ?";

    try (Connection connection = getConnection();
        PreparedStatement pStmt = connection.prepareStatement(query)) {

      pStmt.setInt(1, middleId);
      try (ResultSet rs = pStmt.executeQuery()) {
        while (rs.next()) {
          SubclassCategory category = new SubclassCategory(
              rs.getInt("subclass_id"),
              rs.getString("subclass_name"),
              rs.getInt("middle_id")
          );
          subclassCategory.add(category);
        }
      }
    }
    return subclassCategory;
  }
}
