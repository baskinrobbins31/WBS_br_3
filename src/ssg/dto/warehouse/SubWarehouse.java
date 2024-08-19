package ssg.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class SubWarehouse {
  private int  wsId;
  private String wsType; // ENUM -> String 타입
  private int wssNumber; //섹션 창고 숫자
  private Float wsAreaSqm; //창고 토지면적
  private Float wsWSqm; //창고 전용 면적
  private Float wsHeight;
  private int wId;
}
