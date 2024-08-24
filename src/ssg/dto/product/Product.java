package ssg.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private int productId;
  private String productName;
  private int productPrice;
  private String productInfo;
  private int subclassId;
  private String subclassName;
  private String middleName;
  private String majorName;

  public Product(String productName, int productPrice, String productInfo, int subclassId) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productInfo = productInfo;
    this.subclassId = subclassId;
  }

  public Product(String productName, int productPrice, String productInfo,
      String subclassName, String middleName, String majorName) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productInfo = productInfo;
    this.subclassName = subclassName;
    this.middleName = middleName;
    this.majorName = majorName;
  }

}

