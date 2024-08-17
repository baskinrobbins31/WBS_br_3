package ssg.service;

import java.sql.SQLException;
import java.util.List;
import ssg.dao.inbound.ProductDAO;
import ssg.dto.Product;

public class ProductService {
  private ProductDAO productDAO;

  public ProductService(ProductDAO productDAO){
    this.productDAO = productDAO;
  }

  public boolean isProductExist(String productName) throws SQLException {
    return productDAO.findProductByName(productName).isPresent();
  }

  public void createProduct(Product product) throws SQLException {


    productDAO.create(product);
    System.out.println("상품이 등록되었습니다.");
  }

  public void readProduct() throws SQLException {

  }

  public void updateProduct(Product product) throws SQLException {
    productDAO.update(product);
    System.out.println("상품이 업데이트 되었습니다.");
  }

  public void deleteProduct(String productName) throws SQLException {
    productDAO.delete(productName);
    System.out.println("상품이 삭제되었습닍다.");
  }

}
