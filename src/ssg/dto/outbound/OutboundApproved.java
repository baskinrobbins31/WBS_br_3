package ssg.dto.outbound;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutboundApproved {
  private int outId;          // 출고 번호 (Primary Key)
  private int userId;         // 사용자 ID (Foreign Key)
  private int stockId;        // 재고 ID (Foreign Key)
  private int wssId;          // 창고 구역 ID (Foreign Key)
  private Integer waybillId;  // 송장 ID (Foreign Key, Nullable)
  private byte outState;      // 출고 상태 (TinyInt, Default 0)
  private Timestamp outFinalTime;  // 최종 출고 시간 (Nullable)
}
