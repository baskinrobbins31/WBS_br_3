package ssg.controller.warehouse;


import java.io.IOException;
import ssg.dto.warehouse.SubWarehouse;
import ssg.enums.UserType;
import ssg.library.script.warehousescript.SubWarehouseScript;
import ssg.library.script.warehousescript.WarehouseScript;
import ssg.service.warehouse.WarehouseService;

public class WarehouseController {
  WarehouseScript s = new WarehouseScript();
  SubWarehouseScript sw = new SubWarehouseScript();
  WarehouseService service = new WarehouseService();

  /*권한 별 메뉴 열기*/
  public void callWarehouseMenu(UserType uType) {
    switch(uType) {
      case ADMINISTRATOR -> warehouseMenuAdmin();
      case WH_ADMIN, PRESIDENT_MEMBER, NORMAL_MEMBER -> warehouseMenuNotAdmin();
    }
  }

  /*관리자 메인 메뉴 컨트롤러*/
  private void warehouseMenuAdmin() {
    try {
      boolean cont = true;
      while (cont) {
        //창고관리 1 : 창고 메인 메뉴
        String menu = s.printWarehouseMenuMain();
        switch (menu) {
          case "1" -> {
            //창고관리 1-1 : 창고 등록 메뉴
            String menu1 = s.printWarehouseMenu1();
            if (menu1.equals("1")) {
              boolean case1 = true;
              while (case1) {
                if (registerWarehouse()) {
                  case1 = registerWarehouseSuccess();
                } else {
                  s.registerFail();
                  case1 = false;
                }
              }
            } else if (menu1.equals("2")) {
                //창고관리 1-1-2 :서브 창고 등록
                boolean repeatSub = true;
                while (repeatSub) {
                  if (registerSubWarehouse()) {
                    String menu1_2 = s.registerSuccess();
                    repeatSub = registerSubWarehouseSuccess(menu1_2);
                  } else {
                    s.registerFail();
                    repeatSub = false;
                  }
                }
              }
            }
          case "2" -> {
            //창고관리 1-2 : 창고 조회 메뉴
            boolean repeatMenu2 = true;
            while(repeatMenu2) {
              repeatMenu2 = searchWarehouse();
            }
          }
          default -> cont = false;
          }
        }
    } catch (IOException e) {
      System.out.println("입력 중 문제가 발생했습니다." + e.getMessage());
    }
  }

  /*관리자 외 메인 메뉴 컨트롤러*/
  private void warehouseMenuNotAdmin() {
    try {
      boolean cont = true;
      while (cont) {
        //창고관리 1 : 창고 메인 메뉴
        String menu = s.printWarehouseMenuMainNotAdmin();
        switch (menu) {
          case "1" -> {
            //창고관리 1-2 : 창고 조회 메뉴
            boolean repeatMenu2 = true;
            while(repeatMenu2) {
              repeatMenu2 = searchWarehouse();
            }
          }
          case "2" -> cont = false;
        }
      }
    } catch (IOException e) {
      System.out.println("입력 중 문제가 발생했습니다." + e.getMessage());
    }
  }

  /*창고 등록 메뉴*/
  private boolean registerWarehouse() throws IOException {
    System.out.println("\n<창고 시설 등록>");
    String wName = s.registerWarehouseName();
    int locationId = s.registerWarehouseLocationId();
    String location = s.registerWarehouseLocation();
    String totalAreaSqm = s.registerWarehouseArea();
    String generalAreaSqm = s.registerWarehouseGeneralArea();
    String coldAreaSqm = s.registerWarehouseColdArea();
    String storageAreaSqm = s.registerWarehouseStorageArea();
    String portAreaSqm = s.registerWarehousePortArea();
    String bondedAreaSqm = s.registerWarehouseBondedArea();
    String chemicalAreaSqm = s.registerWarehouseChemicalArea();
    String foodAreaSqm = s.registerWarehouseFoodArea();
    String livestockAreaSqm = s.registerWarehouseLivestockArea();
    String marineAreaSqm = s.registerWarehouseMarineArea();
    int law = s.registerWarehouseLaw(); //법률 코드 -> 법률 문자열로 변환 필
    String items = s.registerWarehouseItems();
    String manager = s.registerWarehouseManager();
    String employeesNumber = s.registerWarehouseEmployees();
    String equipments = s.registerWarehouseEquipment();
    String warehouseContact = s.registerWarehouseContact();

    if (s.registerOk() == 1) {
      String relatedLaw = service.getWarehouseLaw(law);
      StringBuilder sql = new StringBuilder();
      sql.append(
              "INSERT INTO warehouse (w_name, location, location_id, total_area_sqm, " +
                  "general_w_sqm, cold_w_sqm, storage_w_sqm, port_w_sqm, bonded_w_sqm, chemical_w_sqm, "
                  +
                  "food_cold_w_sqm, livestock_w_sqm, marine_cold_w_sqm, related_law, handled_items, "
                  +
                  "manager, employees_number, facility_equipment, contact_number) VALUES (")
          .append("'").append(wName).append("', ")
          .append("'").append(location).append("', ")
          .append(locationId).append(", ")
          .append(totalAreaSqm).append(", ")
          .append(generalAreaSqm != null ? generalAreaSqm : "NULL").append(", ")
          .append(coldAreaSqm != null ? coldAreaSqm : "NULL").append(", ")
          .append(storageAreaSqm != null ? storageAreaSqm : "NULL").append(", ")
          .append(portAreaSqm != null ? portAreaSqm : "NULL").append(", ")
          .append(bondedAreaSqm != null ? bondedAreaSqm : "NULL").append(", ")
          .append(chemicalAreaSqm != null ? chemicalAreaSqm : "NULL").append(", ")
          .append(foodAreaSqm != null ? foodAreaSqm : "NULL").append(", ")
          .append(livestockAreaSqm != null ? livestockAreaSqm : "NULL").append(", ")
          .append(marineAreaSqm != null ? marineAreaSqm : "NULL").append(", ")
          .append("'").append(relatedLaw).append("', ")
          .append(items != null ? "'" + items + "'" : "NULL").append(", ")
          .append("'").append(manager).append("', ")
          .append(employeesNumber != null ? employeesNumber : "NULL").append(", ")
          .append(equipments != null ? "'" + equipments + "'" : "NULL").append(", ")
          .append(warehouseContact != null ? "'" + warehouseContact + "'" : "NULL")
          .append(");");
        String query = sql.toString();
        return service.executeQuery(query);
    }
    else {
      return false;
    }
  }

  /*창고 등록 성공 시 메뉴*/
  private boolean registerWarehouseSuccess() throws IOException {
    String menu1_1 = s.registerSuccess();
    switch (menu1_1) {
      case "1" -> {
        //창고 전체 조회 -> 최신순으로 조회, 10개 리밋
        s.printWarehouseList(service.getWarehouseListLimit(10));
        return true;
      }
      case "2" -> {return true;}
      case "3" -> {return false;}
    }
    return false;
  }

  /*서브창고 등록 메뉴*/
  private boolean registerSubWarehouse() throws IOException {
    System.out.println("\n<층별 창고 등록>");
    String wId = sw.printSubWarehouseParentID();
    String wID = sw.registerSubWarehouseParentID(wId, service.getWarehouseListColumn("w_id"));
    int wsType = sw.registerWarehouseLaw();
    String type = service.getSubWarehouseLaw(wsType);
    System.out.print("창고 토지 면적을 입력해주세요 : ");
    String wsAreaSqm = sw.registerSubWarehouseArea();
    System.out.print("창고 전용 면적을 입력해주세요 : ");
    String wsWSqm = sw.registerSubWarehouseArea();
    System.out.print("층고를 입력해주세요 : ");
    String wsHeight = sw.registerSubWarehouseArea();
    if (s.registerOk() == 1) {
      SubWarehouse subWarehouse = SubWarehouse.builder().wId(Integer.parseInt(wID)).wsType(type)
          .wssNumber(Integer.parseInt(wsWSqm)).wsAreaSqm(Float.valueOf(wsAreaSqm))
          .wsWSqm(Float.valueOf(wsWSqm)).wsHeight(Float.valueOf(wsHeight)).build();
      return service.executeQuery(subWarehouse);
    }
    return false;
  }

  /*서브창고 등록 성공 시 메뉴*/
  private boolean registerSubWarehouseSuccess(String menu1_2) throws IOException {
    switch (menu1_2) {
      case "1" -> {
        //창고 전체 조회 -> 최신순으로 조회, 10개 리밋
        sw.printSubWarehouseList(service.getSubWarehouseListLimit(10));
      }
      case "2" -> {return true;}
      case "3" -> {return false;}
    }
    return false;
  }

  /*창고 조회 메뉴*/
  private boolean searchWarehouse() throws IOException {
    String menu2 = s.printWarehouseMenu2();
    switch (menu2) {
      case "1" -> {
        //창고관리 1-2-1 : 창고 전체 조회
        System.out.println("\n<창고 전체 조회>\n");
        s.printWarehouseList(service.getWarehouseListAll());
        return true;
      }
      case "2" -> {
        //창고관리 1-2-2 : 창고 소재지 별 조회
        System.out.println("\n<창고 소재지 별 조회>\n");
        int id = s.registerWarehouseLocationId();
        s.printWarehouseList(service.getWarehouseListLocationId(id));
        return true;
      }
      case "3" -> {
        //창고관리 1-2-3 : 창고명 조회
        System.out.println("\n<창고명 조회>\n");
        String name = s.registerWarehouseName();
        s.printWarehouseList(service.getWarehouseListName(name));
        return true;
      }
      case "4" -> {
        //창고관리 1-2-4 : 창고 종류(법률) 별 조회
        System.out.println("\n<창고 법률 별 조회>\n");
        String related_law = service.getWarehouseLaw(s.registerWarehouseLaw());
        s.printWarehouseList(service.getWarehouseListLaw(related_law));
        return true;
      }
      case "5" -> {
      }
      default -> {
        return false;
      }
    }
    return false;
  }

}