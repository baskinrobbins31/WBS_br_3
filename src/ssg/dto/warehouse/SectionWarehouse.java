package ssg.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SectionWarehouse {
  private int sswId;
  private Float rentFee6m; // FLOAT -> Float 타입 (null 허용)
  private Float rentFee12m; // FLOAT -> Float 타입 (null 허용)
  private int swId;
}
