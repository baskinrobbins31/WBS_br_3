package ssg.service.warehouse;

import java.util.List;
import ssg.dto.warehouse.Warehouse;

public interface WarehouseServiceInterface {
  String getWarehouseLaw(int law);
  boolean executeQuery(String query);
  List<Warehouse> getWarehouseListAll();
  List<Warehouse> getWarehouseListLocationId(int locationId);
  List<Warehouse> getWarehouseListLaw(String law);
  List<Warehouse> getWarehouseListName(String name);
}
