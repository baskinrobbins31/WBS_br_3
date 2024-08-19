package ssg.library.script.warehousescript;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import ssg.dto.warehouse.Warehouse;
import ssg.enums.errorcode.ErrorCode;
import ssg.exception.Exception;
import ssg.exception.ExceptionList;

public class WarehouseScript {
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  //창고관리 1 : 창고 메인 메뉴 , 총관리자 권한 일때
  public String printWarehouseMenuMain() throws IOException {
    System.out.print("\n<창고 관리>\n1. 창고 등록\t\t\t2. 창고 조회\t\t\t3. 나가기\n\n메뉴 입력 : ");
      String menu = reader.readLine();
      if (ExceptionList.isNumberInRange(menu,3)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return menu;
  }

  //창고관리 : 총관리자 권한이 아닐 때
  public String printWarehouseMenuMainNotAdmin() throws IOException {
    System.out.print("\n<창고 관리>\n1. 창고 조회\t\t\t2. 나가기\n\n메뉴 입력 : ");
    String menu = reader.readLine();
    if (ExceptionList.isNumberInRange(menu,2)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return menu;
  }

  //창고관리 1-1 : 창고 등록 메뉴
  public String printWarehouseMenu1() throws IOException {
    System.out.print("\n<창고 등록>\n1. 창고 시설 등록\t\t\t2. 층별 창고 등록\t\t\t3. 세부 창고 등록\n\n메뉴 입력 : ");
    String menu = reader.readLine();
    if (ExceptionList.isNumberInRange(menu, 2)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return menu;
  }

  //창고관리 1-1-1 : 창고 시설 등록 메뉴
  //창고 이름
  public String registerWarehouseName() throws IOException {
    System.out.print("창고명을 입력해주세요 : ");
    String wName = reader.readLine();
    if (ExceptionList.isValidString(wName) || ExceptionList.isLength50(wName) || wName == null) { //유효성 검사
      throw new Exception(ErrorCode.INVALID_INPUT_LENGTH);
    }
    return wName;
  }

  //창고 주소 코드
  public int registerWarehouseLocationId() throws IOException{
    System.out.print("창고 주소 코드를 입력해주세요 :\n1. 서울 2. 부산 3. 경기 4. 강원 5. 충북 \n6. 충남 7. 전북 8. 전남 9. 경북 10. 경남\n");
    String locationId = reader.readLine(); //창고 주소 코드번호
    if (ExceptionList.isNumberRange1To10(locationId)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return Integer.parseInt(locationId);
  }

  //창고 주소
  public String registerWarehouseLocation() throws IOException{
    System.out.print("창고 주소를 입력해주세요 : ");
    String location = reader.readLine(); //창고 주소
    if (ExceptionList.isValidAddress(location) || ExceptionList.isLength50(location) || location == null) {
      throw new Exception(ErrorCode.INVALID_INPUT_ADDRESS);
    }
    return location;
  }

  //창고 면적
  public String registerWarehouseArea() throws IOException{
    System.out.print("창고 총 면적을 입력해주세요 : ");
    String area = reader.readLine();
    if (ExceptionList.isValidFloat(area)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return area;
  }

  //일반 창고 면적
  public String registerWarehouseGeneralArea() throws IOException{
    System.out.print("일반 창고 면적을 입력해주세요 : ");
    String generalAreaSqm = reader.readLine();
    if (!generalAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(generalAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return generalAreaSqm;
    }
    return null;
  }

  //냉동냉장 창고 면적
  public String registerWarehouseColdArea() throws IOException{
    System.out.print("냉동/냉장 창고 면적을 입력해주세요 : ");
    String coldAreaSqm = reader.readLine();
    if (!coldAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(coldAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return coldAreaSqm;
    }
    return null;
  }

  //보관장소 창고 면적
  public String registerWarehouseStorageArea() throws IOException{
    System.out.print("보관 창고 면적을 입력해주세요 : ");
    String storageAreaSqm = reader.readLine();
    if (!storageAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(storageAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return storageAreaSqm;
    }
    return null;
  }

  //항만창고 창고 면적
  public String registerWarehousePortArea() throws IOException{
    System.out.print("항만 창고 면적을 입력해주세요 : ");
    String portAreaSqm = reader.readLine();
    if (!portAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(portAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return portAreaSqm;
    }
    return null;
  }

  //보세창고 창고 면적
  public String registerWarehouseBondedArea() throws IOException{
    System.out.print("보세 창고 면적을 입력해주세요 : ");
    String bondedAreaSqm = reader.readLine();
    if (!bondedAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(bondedAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return bondedAreaSqm;
    }
    return null;
  }

  //화학물질 창고 면적
  public String registerWarehouseChemicalArea() throws IOException{
    System.out.print("화학 창고 면적을 입력해주세요 : ");
    String chemicalAreaSqm = reader.readLine();
    if (!chemicalAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(chemicalAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return chemicalAreaSqm;
    }
    return null;
  }

  //식품위생 창고 면적
  public String registerWarehouseFoodArea() throws IOException{
    System.out.print("식품 창고 면적을 입력해주세요 : ");
    String foodAreaSqm = reader.readLine();
    if (!foodAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(foodAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return foodAreaSqm;
    }
    return null;
  }

  //축산물 창고 면적
  public String registerWarehouseLivestockArea() throws IOException{
    System.out.print("축산물 창고 면적을 입력해주세요 : ");
    String livestockAreaSqm = reader.readLine();
    if (!livestockAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(livestockAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return livestockAreaSqm;
    }
    return null;
  }

  //수산식품 창고 면적
  public String registerWarehouseMarineArea() throws IOException{
    System.out.print("수산 창고 면적을 입력해주세요 : ");
    String marineAreaSqm = reader.readLine();
    if (!marineAreaSqm.trim().isEmpty()) {
      if (ExceptionList.isValidFloat(marineAreaSqm)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return marineAreaSqm;
    }
    return null;
  }

  //창고 관련 법률
  public int registerWarehouseLaw() throws IOException{
    System.out.print("창고 시설을 입력해주세요 :\n1. 물류시설법 2. 관세법 3. 물류시설법(항만) 4. 수산식품산업법 5. 식품위생법\n6. 축산물위생법 7. 화학물질관리법\n");
    String law = reader.readLine();
    if (ExceptionList.isNumberRange1To7(law)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return Integer.parseInt(law); //
  }

  //취급 품목
  public String registerWarehouseItems() throws IOException{
    System.out.print("취급 품목을 입력해주세요 : ");
    String items = reader.readLine();
    if (!items.trim().isEmpty()) {
      if (ExceptionList.isValidText(items) || ExceptionList.isLength50(items)) {
        throw new Exception(ErrorCode.INVALID_INPUT_LENGTH);
      }
      return items;
    }
    return null;
  }

  //창고 관리자
  public String registerWarehouseManager() throws IOException{
    System.out.print("창고 관리자 이름을 입력해주세요 : ");
    String manager = reader.readLine();
    if (ExceptionList.isValidString(manager) || ExceptionList.isLength50(manager) || manager == null) {
      throw new Exception(ErrorCode.INVALID_INPUT_LENGTH);
    }
    return manager;
  }

  //등록일 : Controller에서 바로 입력

  //종업원 수
  public String registerWarehouseEmployees() throws IOException{
    System.out.print("종업원 수를 입력해주세요 : ");
    String employees = reader.readLine();
    if (!employees.trim().isEmpty()) {
      if (ExceptionList.isValidNumber(employees) || ExceptionList.isValidSmallInt(employees)) {
        throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
      }
      return employees;
    }
    return null;
  }

  //시설 장비
  public String registerWarehouseEquipment() throws IOException{
    System.out.print("시설 장비를 입력해주세요 : ");
    String equipment = reader.readLine();
    if (!equipment.trim().isEmpty()) {
      if (ExceptionList.isValidText(equipment) || ExceptionList.isLength50(equipment)) {
        throw new Exception(ErrorCode.INVALID_INPUT_LENGTH);
      }
      return equipment;
    }
    return null;
  }

  //창고 전화번호
  public String registerWarehouseContact() throws IOException{
    System.out.print("연락처를 입력해주세요 : ");
    String contact = reader.readLine();
    if (!contact.trim().isEmpty()) {
      if (ExceptionList.isValidPhoneNumber(contact)) {
        throw new Exception(ErrorCode.INVALID_INPUT_PHONENUMBER);
      }
      return contact;
    }
    return null;
  }

  //등록하기
  public int registerOk() throws IOException {
    System.out.print("\n등록하시겠습니까? (Y / N) : ");
    String ok = reader.readLine();
    if (ExceptionList.isValidOk(ok)) {
      throw new Exception(ErrorCode.INVALID_INPUT_OKAY);
    }
    if ("Y".equalsIgnoreCase(ok)) {
      return 1;
    } else {
      return 0;
    }
  }

  //등록 성공
  public String registerSuccess() throws IOException {
    System.out.print("창고가 등록되었습니다.\n\n1. 창고 전체 조회\t\t\t2. 계속 등록하기\t\t\t3. 나가기\n\n메뉴 입력 : ");
    String menu = reader.readLine();
    if (ExceptionList.isNumberRange1To3(menu)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return menu;
  }

  //등록 실패
  public void registerFail() throws IOException {
    System.out.println("창고 등록이 실패했습니다.");
  }

  //창고관리 1-2 : 창고 조회 메뉴
  public String printWarehouseMenu2() throws IOException {
    out.print(
        "\n<창고 조회>\n1. 전체 조회\t\t\t2. 소재지 별 조회\t\t\t3. 창고명 별 조회\n4. 종류 별 조회\t\t\t5. 임대여부 별 조회\t\t\t6. 나가기\n\n메뉴 입력 : ");
    String menu = reader.readLine();
    if (ExceptionList.isNumberRange1To6(menu)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
    return menu;
  }

  //List 출력
  public void printWarehouseList(List<Warehouse> warehouseList) {
    Optional<List<Warehouse>> optional = Optional.ofNullable(warehouseList);
    optional.ifPresentOrElse(
        warehouseArray -> warehouseArray.forEach(warehouse -> {out.println(warehouse.toString());}),
        () -> out.println("창고 리스트가 존재하지 않습니다."));
  }




}
