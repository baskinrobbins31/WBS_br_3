package ssg.service.warehouse;

import java.util.ArrayList;
import java.util.List;
import ssg.dao.warehouse.WarehouseDAO;
import ssg.dto.warehouse.SubWarehouse;
import ssg.dto.warehouse.Warehouse;

public class WarehouseService implements WarehouseServiceInterface{
  WarehouseDAO dao = new WarehouseDAO();

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

  @Override
  public String getSubWarehouseLaw(int law) {
    return switch (law) {
      case 1 -> "물류시설법 일반창고";
      case 2 -> "물류시설법 냉동냉장";
      case 3 -> "물류시설법 보관장소";
      case 4 -> "항만창고";
      case 5 -> "관세법 보세창고";
      case 6 -> "화학물질관리법 보관저장업";
      case 7 -> "식품위생법 냉동냉장";
      case 8 -> "축산물위생법 축산물보관";
      case 9 -> "수산식품산업법 냉동냉장";
      default -> "물류시설법 일반창고";
    };
  }

  @Override
  public boolean executeQuery(String query) {
    return dao.executeQuery(query);
  }

  @Override
  public boolean executeQuery(SubWarehouse sw) {
    return dao.executeQuery(sw);
  }

  @Override
  public List<Warehouse> getWarehouseListAll() {
    return dao.readAll();
  }

  @Override
  public List<Warehouse> getWarehouseListLocationId(int locationId) {
    return dao.readAllLocationId(locationId);
  }

  @Override
  public List<Warehouse> getWarehouseListLaw(String law) {
    return dao.readAllLaw(law);
  }

  @Override
  public List<Warehouse> getWarehouseListName(String name) {
    return dao.readAllName(name);
  }

  @Override
  public List<Warehouse> getWarehouseListLimit(int limit) {
    return dao.readAllLimit(limit);
  }

  @Override
  public List<SubWarehouse> getSubWarehouseListLimit(int limit) {
    return dao.readAllSubWarehouse(limit);
  }

  @Override
  public ArrayList<Integer> getWarehouseListColumn(String column) {
    return dao.readColumn(column);
  }

}



