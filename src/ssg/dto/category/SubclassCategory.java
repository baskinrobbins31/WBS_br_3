package ssg.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubclassCategory {
  private int subclassId;
  private String subclassName;
  private int middleId;
}