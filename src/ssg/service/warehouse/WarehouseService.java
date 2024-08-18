package ssg.service.warehouse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class WarehouseService implements WarehouseServiceInterface{

  @Override
  public String getWarehouseLaw(int law) {
    return switch (law) {
      case 1 -> "물류시설법";
      case 2 -> "관세법";
      case 3 -> "물류시설법(항만)";
      case 4 -> "수산식품산업법";
      case 5 -> "식품위생법";
      case 6 -> "축산물위생법";
      case 7 -> "화학물질관리법";
      default -> "물류시설법";
    };
  }


}



