package ssg.service.stockhistory;

import ssg.dao.stockhistory.StockHistoryDAO;
import ssg.dto.stockhistory.StockHistory;

import java.util.List;

public class StockHistoryService {

    StockHistoryDAO stockHistoryDAO = new StockHistoryDAO();
    public List<StockHistory> readStockHistory() {
        return stockHistoryDAO.readAll();
    }
}
