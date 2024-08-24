package ssg.library.script;

public class CustomerServiceScript {

    StringBuilder sb = new StringBuilder();

    public void printMenu() {
        sb.setLength(0);
        sb.append("\n---고객 센터 메뉴---\n")
                .append("1. 공지사항\t\t")
                .append("2. 문의글\t\t")
                .append("3. 이전화면");
        System.out.println(sb);
    }

    public void printNoticeMenu() {
        sb.setLength(0);
        sb.append("\n1. 공지사항 조회\t")
                // 권한에 따라 나누기
                .append("2. 공지사항 등록\t\t")
                .append("3. 공지사항 수정\t\t")
                .append("4. 공지사항 삭제\t\t")
                .append("5. 돌아가기");
        System.out.println(sb);
    }

    public void printInquiryMenu() {
        sb.setLength(0);
        sb.append("1. 문의글 조회\t\t")
                .append("2. 문의글 등록\t\t")
                .append("3. 문의글 수정\t\t")
                .append("4. 문의글 삭제\t\t")
                .append("5. 돌아가기");
        System.out.println(sb);
    }

}
