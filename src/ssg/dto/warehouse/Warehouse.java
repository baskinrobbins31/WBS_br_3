package ssg.dto.warehouse;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Warehouse {
  private int wId;
  private String wName;
  private String location;
  private byte locationId;
  private float totalAreaSqm;
  private Float generalWSqm;
  private Float coldWSqm;
  private Float storageWSqm;
  private Float portWSqm;
  private Float bondedWSqm;
  private Float chemicalWSqm;
  private Float foodColdWSqm;
  private Float livestockWSqm;
  private Float marineColdWSqm;
  private String relatedLaw;
  private String handledItems;
  private String manager;
  private Timestamp regiDate;
  private Short  employeesNumber;
  private String facilityEquipment;
  private String contactNumber;
}
