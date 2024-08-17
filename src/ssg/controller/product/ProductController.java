package ssg.controller.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.service.ProductService;

public class ProductController {
  private ProductService productService;

  public ProductController (ProductService productService) {
    this.productService = productService;
  }

  public void processProducts() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.println("1. 상품 등록");

      try {
        int choice = Integer.parseInt(br.readLine());
      } catch (IOException e) {
        System.err.println(e);
      }
    }
  }

}
