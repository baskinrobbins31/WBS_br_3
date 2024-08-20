package ssg.controller.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import ssg.dto.product.Product;
import ssg.service.product.ProductService;
import java.util.Optional;

public class ProductController {

  private final ProductService productService;
  private final BufferedReader br;

  public ProductController(ProductService productService, BufferedReader br) {
    this.productService = productService;
    this.br = br;
  }

  public void processProducts() {
    try {
      while (true) {
        StringBuilder menu = new StringBuilder();
        menu.append("1.상품 등록\t\t")
            .append("2.상품 조회 및 관리\t\t")
            .append("3.종료");

        System.out.println(menu.toString());
        System.out.print("선택: ");

        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
          case 1 -> createProduct(br);
          case 2 -> readAndManageProduct(br);
          case 3 -> {
            System.out.println("종료합니다.");
            return;
          }
          default -> System.out.println("잘못된 선택입니다.");
        }
      }
    } catch (IOException | SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  // 상품 등록 메서드
  private void createProduct(BufferedReader br) throws IOException, SQLException {
    productService.displayMajorCategories();
    System.out.print("대분류를 선택하세요: ");
    int majorId = Integer.parseInt(br.readLine());

    productService.displayMiddleCategories(majorId);
    System.out.print("중분류를 선택하세요: ");
    int middleId = Integer.parseInt(br.readLine());

    productService.displaySubclassCategories(middleId);
    System.out.print("소분류를 선택하세요: ");
    int subclassId = Integer.parseInt(br.readLine());

    System.out.print("상품 이름: ");
    String name = br.readLine();
    System.out.print("상품 가격: ");
    int price = Integer.parseInt(br.readLine());
    System.out.print("상품 정보: ");
    String info = br.readLine();

    Product product = new Product(name, price, info, subclassId);
    productService.createProduct(product);
  }

  // 상품 조회 및 관리 메서드
  private void readAndManageProduct(BufferedReader br) throws IOException, SQLException {
    System.out.print("조회할 상품 키워드: ");
    String name = br.readLine();

    List<Product> similarProducts = productService.findProductsByName(name);
    if (similarProducts.isEmpty()) {
      System.out.println("유사한 상품이 없습니다.");
    } else {
      System.out.println("조회된 상품 목록:");
      for (Product product : similarProducts) {
        System.out.println(
            product.getMajorName() + "> "
                + product.getMiddleName() + "> "
                + product.getSubclassName() + ">\t"
                + "\t상품명: " + product.getProductName()
                + "\t\t상품가격: " + product.getProductPrice()
                + "\t상품정보: " + product.getProductInfo());
      }

      System.out.println("관리할 상품의 이름을 입력하세요: ");
      String manageProductName = br.readLine();
      Optional<Product> productOpt = productService.getProductsByName(manageProductName);

      if (productOpt.isPresent()) {
        Product product = productOpt.get();
        System.out.println("1. 상품 업데이트");
        System.out.println("2. 상품 삭제");
        System.out.println("3. 취소");
        System.out.print("선택: ");

        int manageChoice = Integer.parseInt(br.readLine());
        switch (manageChoice) {
          case 1 -> updateProduct(br, product);
          case 2 -> deleteProduct(br, product.getProductName());
          case 3 -> System.out.println("취소되었습니다.");
          default -> System.out.println("잘못된 선택입니다.");
        }
      } else {
        System.out.println("해당 이름의 상품이 존재하지 않습니다.");
      }
    }
  }

  // 상품 업데이트 메서드
  private void updateProduct(BufferedReader br, Product product) throws IOException, SQLException {
    System.out.print("새로운 상품 가격: ");
    int price = Integer.parseInt(br.readLine());
    System.out.print("새로운 상품 정보: ");
    String info = br.readLine();

    product.setProductPrice(price);
    product.setProductInfo(info);
    productService.updateProduct(product);
  }

  // 상품 삭제 메서드
  private void deleteProduct(BufferedReader br, String productName)
      throws IOException, SQLException {
    System.out.println("정말로 상품정보를 삭제하시겠습니까?");
    System.out.println("1.예\t\t2.아니오");
    System.out.print("선택: ");
    int choice = Integer.parseInt(br.readLine());
    if (choice == 1) {
      productService.deleteProduct(productName);
    } else if (choice == 2) {
      System.out.println("상품정보 삭제를 취소하셨습니다.");
    } else {
      System.out.println("옳지않은 입력입니다.");
    }
  }
}

//입고 테이ㅏ블에는 전부 들어가있는데?
//입고 요청테이블에 전부다 들어가ㅗ 요청테이블에서 상태를 바꿔서~~
