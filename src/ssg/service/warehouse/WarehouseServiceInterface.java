package ssg.service.warehouse;

import java.util.ArrayList;
import java.util.List;
import ssg.dto.warehouse.SubWarehouse;
import ssg.dto.warehouse.Warehouse;

public interface WarehouseServiceInterface {
  String getWarehouseLaw(int law);
  String getSubWarehouseLaw(int law);
  boolean executeQuery(String query);
  boolean executeQuery(SubWarehouse sw);
  List<Warehouse> getWarehouseListAll();
  List<Warehouse> getWarehouseListLocationId(int locationId);
  List<Warehouse> getWarehouseListLaw(String law);
  List<Warehouse> getWarehouseListName(String name);
  List<Warehouse> getWarehouseListLimit(int limit);
  List<SubWarehouse> getSubWarehouseListLimit(int limit);
  ArrayList<Integer> getWarehouseListColumn(String column);
}
