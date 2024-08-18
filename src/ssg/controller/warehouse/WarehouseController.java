package ssg.controller.warehouse;

import java.io.IOException;
import ssg.library.script.warehousescript.WarehouseScript;
import ssg.service.warehouse.WarehouseService;

public class WarehouseController {
  WarehouseScript s = new WarehouseScript();
  WarehouseService service = new WarehouseService();

  public void callWarehouseMenu(){
    try {
      String menu = s.printWarehouseMenuMain();
      //창고관리 1 : 창고 메인 메뉴
      switch(menu) {
        case "1" -> {
          //창고관리 1-1 : 창고 등록 메뉴
          String menu2 = s.printWarehouseMenu1();
          //창고관리 1-1-1 : 창고 시설 등록 메뉴
          if (menu2.equals("1")) {
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
              sql.append("INSERT INTO warehouse (w_name, location, location_id, total_area_sqm, " +
                  "general_w_sqm, cold_w_sqm, storage_w_sqm, port_w_sqm, bonded_w_sqm, chemical_w_sqm, " +
                  "food_cold_w_sqm, livestock_w_sqm, marine_cold_w_sqm, related_law, handled_items, " +
                  "manager, employees_number, facility_equipment, contact_number) VALUES (")
                  .append("'").append(wName).append("', ")
                  .append("'").append(location).append("', ")
                  .append(locationId).append(", ")
                  .append(totalAreaSqm).append(", ")
                  .append(generalAreaSqm != null ? generalAreaSqm : "NULL").append(", ")
                  .append(coldAreaSqm != null ? coldAreaSqm : "NULL").append(", ")
                  .append(storageAreaSqm!= null ? storageAreaSqm : "NULL").append(", ")
                  .append(portAreaSqm != null ? portAreaSqm : "NULL").append(", ")
                  .append(bondedAreaSqm != null ? bondedAreaSqm : "NULL").append(", ")
                  .append(chemicalAreaSqm!= null ? chemicalAreaSqm : "NULL").append(", ")
                  .append(foodAreaSqm!= null ? foodAreaSqm : "NULL").append(", ")
                  .append(livestockAreaSqm != null ? livestockAreaSqm : "NULL").append(", ")
                  .append(marineAreaSqm != null ? marineAreaSqm : "NULL").append(", ")
                  .append("'").append(relatedLaw).append("', ")
                  .append(items != null ? "'" + items + "'" : "NULL").append(", ")
                  .append("'").append(manager).append("', ")
                  .append(employeesNumber != null ? employeesNumber :  "NULL").append(", ")
                  .append(equipments != null ? "'" + equipments + "'" : "NULL").append(", ")
                  .append(warehouseContact != null ? "'" + warehouseContact + "'" : "NULL")
                  .append(");");
              String query = sql.toString();

            } else {
              break;
            }
          }
        }
        case "2" -> {}
        case "3" -> {}
      }
    } catch (IOException e) {
      System.out.println("입력 중 문제가 발생했습니다." + e.getMessage());
    }
  }
}