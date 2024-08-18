package ssg.service.product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ssg.dao.category.CategoryDAO;
import ssg.dao.product.ProductDAO;
import ssg.dto.Product;
import ssg.dto.category.MajorCategory;
import ssg.dto.category.MiddleCategory;
import ssg.dto.category.SubclassCategory;

public class ProductService {
  private final ProductDAO productDAO;
  private final CategoryDAO categoryDAO;

  public ProductService(ProductDAO productDAO, CategoryDAO categoryDAO) {
    this.productDAO = productDAO;
    this.categoryDAO = categoryDAO;
  }

  public boolean isProductExist(String productName) throws SQLException {
    return productDAO.findProductByName(productName).isPresent();
  }

  public void createProduct(Product product) throws SQLException {
    if (!isProductExist(product.getProductName())) {
      productDAO.create(product);
      System.out.println("상품이 등록되었습니다.");
    } else {
      System.out.println("이미 존재하는 상품입니다.");
    }
  }

  public void updateProduct(Product product) throws SQLException {
    if (isProductExist(product.getProductName())) {
      productDAO.update(product);
      System.out.println("상품이 업데이트 되었습니다.");
    }
  }

  public void deleteProduct(String productName) throws SQLException {
    if (isProductExist(productName)) {
      productDAO.delete(productName);
      System.out.println("상품이 삭제되었습니다.");
    }
  }

  public List<Product> findProductsByName(String productName) throws SQLException {
    return productDAO.findProductsByName(productName);
  }

  public List<Product> getAllProduct() throws SQLException {
    return productDAO.readAll();
  }

  public Optional<Product> getProductsByName(String productName) throws SQLException {
    return productDAO.findProductByName(productName);
  }

  public List<MajorCategory> getMajorCategories() throws SQLException {
    return categoryDAO.getMajorCategory();
  }

  public List<MiddleCategory> getMiddleCategories(int majorId) throws SQLException {
    return categoryDAO.getMiddleCategory(majorId);
  }

  public List<SubclassCategory> getSubclassCategories(int middleId) throws SQLException {
    return categoryDAO.getSubclassCategory(middleId);
  }

  public void displayMajorCategories() throws SQLException {
    List<MajorCategory> majorCategories = getMajorCategories();
    String display = majorCategories.stream()
        .map(category -> category.getMajorId() + "." + category.getMajorName())
        .collect(Collectors.joining("   "));
    System.out.println(display);
  }

  public void displayMiddleCategories(int majorId) throws SQLException {
    List<MiddleCategory> middleCategories = getMiddleCategories(majorId);
    String display = middleCategories.stream()
        .map(category -> category.getMiddleId() + "." + category.getMiddleName())
        .collect(Collectors.joining("   "));
    System.out.println(display);
  }

  public void displaySubclassCategories(int middleId) throws SQLException {
    List<SubclassCategory> subclassCategories = getSubclassCategories(middleId);
    String display = subclassCategories.stream()
        .map(category -> category.getSubclassId() + "." + category.getSubclassName())
        .collect(Collectors.joining("   "));
    System.out.println(display);
  }
}
