package ssg;

import ssg.controller.product.ProductController;
import ssg.dao.category.CategoryDAO;
import ssg.dao.product.ProductDAO;
import ssg.dto.Member;
import ssg.service.product.ProductService;

public class Main {

  public static Member loginOnMember; //로그인 후 유저 정보를 담고 있음

  public static void main(String[] args) {

    ProductController productController = new ProductController(new ProductService(new ProductDAO(), new CategoryDAO()));

    productController.processProducts();
  }
}
