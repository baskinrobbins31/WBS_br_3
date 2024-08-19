package ssg.controller.stockhistory;

import ssg.dto.stockhistory.StockHistory;
import ssg.library.script.CustomerServiceScript;
import ssg.library.script.StockHistoryScript;
import ssg.service.stockhistory.StockHistoryService;

import java.util.List;
import java.util.Scanner;

public class StockHistoryController {
    StockHistoryScript stockHistoryScript = new StockHistoryScript();

    StockHistoryService stockHistoryService = new StockHistoryService();

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isQuit = false;
        while (!isQuit) {
            stockHistoryScript.printMenu();
            System.out.print("원하는 메뉴를 입력하세요.: ");
            int number = scanner.nextInt();
            switch (number) {
                case 1 -> {
                    List<StockHistory> stockHistories = stockHistoryService.readStockHistory();

                    if (!stockHistories.isEmpty()) {
                        System.out.println("-------- 재고 현황-----------");
                        stockHistories.forEach(stockHistory ->
                            System.out.printf("%d\t%d\t%s\t%s%n",
                                    stockHistory.getId(),
                                    stockHistory.getStockID(),
                                    stockHistory.getStatus(),
                                    stockHistory.getCreatedAt()
                            )
                        );

                    }
                    System.out.println();
                }
                case 2 -> {
                    isQuit = true;
                    System.out.println("이전 화면으로 돌아갑니다.");
                }
            }
        }

    }
}
