package ssg.dao.stockhistory;

import ssg.dto.stockhistory.StockHistory;
import ssg.library.dbio.AbstractDBIO2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockHistoryDAO extends AbstractDBIO2<StockHistory> {


    @Override
    public List<StockHistory> readAll() {
        String query = "SELECT * FROM stockhistory";
        List<StockHistory> stockHistories = new ArrayList<>();

        try {
            Connection connection = getConnection();

            PreparedStatement pstmt = connection.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 재고가 없습니다.");
            } else {
                while (rs.next()) {
                    StockHistory stockHistory = StockHistory.builder()
                            .id(rs.getInt(1))
                            .stockID(rs.getInt(2))
                            .status(rs.getString(3))
                            .createdAt(rs.getTimestamp(4))
                            .build();
                    stockHistories.add(stockHistory);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return stockHistories;
    }
}
