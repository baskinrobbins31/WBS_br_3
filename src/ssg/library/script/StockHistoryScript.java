package ssg.library.script;

public class StockHistoryScript {
    StringBuilder sb = new StringBuilder();
    public void printMenu() {
        sb.setLength(0);
        sb.append("---재고실사---\n")
                .append("1. 재고현황 조회\t")
                .append("2. 이전화면");
        System.out.println(sb);
    }
}
