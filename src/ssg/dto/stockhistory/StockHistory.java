package ssg.dto.stockhistory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockHistory {
    private int id;
    private int stockID;
    private String status;
    private Timestamp createdAt;

    @Builder
    StockHistory(int id, int stockID , String status, Timestamp createdAt) {
        this.id = id;
        this.stockID = stockID;
        this.status = status;
        this.createdAt = (createdAt == null ? new Timestamp(System.currentTimeMillis()) : createdAt);
    }
}
