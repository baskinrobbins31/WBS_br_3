package ssg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
  private int productId;
  private String productName;
  private int productPrice;
  private String productInfo;
  private int subclassId;
}
