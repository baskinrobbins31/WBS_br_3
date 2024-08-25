package ssg.library.script.warehousescript;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ssg.dto.warehouse.SubWarehouse;
import ssg.dto.warehouse.Warehouse;
import ssg.enums.errorcode.ErrorCode;
import ssg.exception.Exception;
import ssg.exception.ExceptionList;

public class SubWarehouseScript {
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  ExceptionList el = new ExceptionList();

  public String registerSubWarehouseParentID (String id, ArrayList<Integer> array) throws IOException {
    if (ExceptionList.isExistWId(id, array)) {
      throw new Exception(ErrorCode.NOT_EXISTS_ID);
    }
    return id;
  }

  public String printSubWarehouseParentID () throws IOException {
    System.out.print("창고 아이디를 입력해주세요 : ");
    String wid = reader.readLine();
    el.throwInvalidNumber(wid);
    return wid;
  }

  //창고 관련 법률
  public int registerWarehouseLaw() throws IOException{
    System.out.print("창고 시설을 입력해주세요 : \n" +
        "1. 물류시설법 일반창고\n" +
        "2. 물류시설법 냉동냉장\n" +
        "3. 물류시설법 보관장소\n" +
        "4. 항만창고\n" +
        "5. 관세법 보세창고\n" +
        "6. 화학물질관리법 보관저장업\n" +
        "7. 식품위생법 냉동냉장\n" +
        "8. 축산물위생법 축산물보관\n" +
        "9. 수산식품산업법 냉동냉장\n");
    String law = reader.readLine();
    el.throwInvalidNumberRange(law, 9);
    return Integer.parseInt(law); //
  }

  public String registerSubWarehouseArea() throws IOException{ //토지 면적
    String sqm = reader.readLine();
    if (!sqm.trim().isEmpty()) {
      el.throwInvalidFloat(sqm);
      return sqm;
    }
    return null;
  }

  //List 출력
  public void printSubWarehouseList(List<SubWarehouse> subWarehouseList) {
    Optional<List<SubWarehouse>> optional = Optional.ofNullable(subWarehouseList);
    optional.ifPresentOrElse(
        subWarehouseArray -> subWarehouseArray.forEach(warehouse -> {out.println(warehouse.toString());}),
        () -> out.println("창고 리스트가 존재하지 않습니다."));
  }

}
