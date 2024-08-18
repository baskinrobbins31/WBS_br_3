package ssg.library.script.warehousescript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.enums.errorcode.ErrorCode;
import ssg.exception.Exception;
import ssg.exception.ExceptionList;

public class SubWarehouseScript {
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public String registerSubWarehouseParentID () throws IOException {
    System.out.print("창고 아이디를 입력해주세요 : ");
    String wid = reader.readLine();
    //창고 아이디가 없으면 throw exception
    if (true) {

    }
    return wid;
  }


  public String registerSubWarehouseArea() throws IOException{
    String sqm = reader.readLine();
    if (!sqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(sqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return sqm;
    }
    return null;
  }

}
