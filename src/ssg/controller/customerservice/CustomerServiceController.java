package ssg.controller.customerservice;

import ssg.dto.customerservice.Inquiry;
import ssg.dto.customerservice.Notice;
import ssg.library.script.CustomerServiceScript;
import ssg.service.customerservice.InquiryService;
import ssg.service.customerservice.NoticeService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class CustomerServiceController {
    CustomerServiceScript customerServiceScript = new CustomerServiceScript();

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isQuit = false;
        while (!isQuit) {
            customerServiceScript.printMenu();
            System.out.print("원하는 메뉴를 입력하세요.: ");
            int number = scanner.nextInt();
            switch (number) {
                case 1 -> {
                    noticeMenu(scanner);
                }
                case 2 -> {
                    inquiryMenu(scanner);
                }
                case 3 -> {
                    isQuit = true;
                    System.out.println("이전 화면으로 돌아갑니다.");
                }
            }
        }

    }

    private void noticeMenu(Scanner scanner) {
        NoticeService noticeService = new NoticeService();
        boolean isQuit = false;
        while (!isQuit) {
            System.out.println("--공지글 메뉴입니다.--");
            customerServiceScript.printNoticeMenu();
            System.out.print("원하는 메뉴를 입력하세요.: ");
            int menu = scanner.nextInt();
            switch (menu) {
                case 1 -> {
                    List<Notice> noticeList = noticeService.getNotices();
                    if (!noticeList.isEmpty()) {
                        System.out.println("-------- 공지글 목록-----------");
                        StringBuilder sb = new StringBuilder();
                        for (Notice notice : noticeList) {
                            sb
                                    .append(notice.getId()).append("\t")
                                    .append(notice.getTitle()).append("\t")
                                    .append(notice.getContent()).append("\t")
                                    .append(notice.getCreatedAt()).append("\t")
                                    .append(notice.getUpdatedAt()).append("\t");
                            System.out.println(sb);
                            sb.setLength(0);
                        }
                    }
                    System.out.println();
                }
                case 2 -> {
                    System.out.println("공지글 등록: ");
                    System.out.print("1. 제목: ");
                    String title = scanner.next();
                    scanner.nextLine();
                    System.out.print("2. 내용: ");
                    String content = scanner.nextLine();

                    Notice notice = Notice.builder()
                            .title(title)
                            .content(content)
                            .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                            .build();

                    noticeService.createNotice(notice);
                    System.out.println();
                }
                case 3 -> {
                    System.out.print("수정할 공지글 id를 입력하세요.: ");
                    int notice_id = scanner.nextInt();
                    System.out.print("수정할 공지글 제목을 입력하세요.: ");
                    String notice_title = scanner.next();
                    scanner.nextLine();
                    System.out.print("수정할 공지글 내용을 입력하세요.: ");
                    String notice_content = scanner.nextLine();

                    Notice notice = Notice.builder()
                            .title(notice_title)
                            .content(notice_content)
                            .updatedAt(new Timestamp(System.currentTimeMillis()))
                            .build();

                    noticeService.updateNotice(notice_id, notice);
                    System.out.println();
                }
                case 4 -> {
                    System.out.print("삭제할 공지글 id를 입력하세요.: ");
                    int notice_id = scanner.nextInt();
                    noticeService.deleteNotice(notice_id);
                    System.out.println();
                }
                case 5 -> {
                    isQuit = true;
                    System.out.println("이전 화면으로 돌아갑니다.");
                }
            }
        }
    }

    private void inquiryMenu(Scanner scanner) {
        InquiryService customerServiceService = new InquiryService();
        boolean isQuit = false;
        while (!isQuit) {
            System.out.println("--문의글 메뉴입니다.--");
            customerServiceScript.printInquiryMenu();
            System.out.print("원하는 메뉴를 입력하세요.: ");
            int menu = scanner.nextInt();
            switch (menu) {
                case 1 -> {
                    List<Inquiry> inquiryList = customerServiceService.getInquiries();
                    if (!inquiryList.isEmpty()) {
                        System.out.println("-------- 나의 문의글 -----------");
                        StringBuilder sb = new StringBuilder();
                        for (Inquiry inquiry : inquiryList) {
                            sb
                                    .append(inquiry.getId()).append("\t")
                                    .append(inquiry.getTitle()).append("\t")
                                    .append(inquiry.getContent()).append("\t")
                                    .append(inquiry.getCreatedAt()).append("\t")
                                    .append(inquiry.getUpdatedAt()).append("\t");
                            System.out.println(sb);
                            sb.setLength(0);
                        }
                    }
                    System.out.println();
                }
                case 2 -> {
                    System.out.println("문의글 등록: ");
                    System.out.print("1. 제목: ");
                    String title = scanner.next();
                    scanner.nextLine();
                    System.out.print("2. 내용: ");
                    String content = scanner.nextLine();

                    Inquiry inquiry = Inquiry.builder()
                            .title(title)
                            .content(content)
                            .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                            .build();

                    customerServiceService.createInquiry(inquiry);
                    System.out.println();
                }
                case 3 -> {
                    System.out.print("수정할 문의글 id를 입력하세요.: ");
                    int inquiry_id = scanner.nextInt();
                    System.out.print("수정할 문의글 제목을 입력하세요.: ");
                    String inquiry_title = scanner.next();
                    scanner.nextLine();
                    System.out.print("수정할 문의글 내용을 입력하세요.: ");
                    String inquiry_content = scanner.nextLine();

                    Inquiry inquiry = Inquiry.builder()
                            .title(inquiry_title)
                            .content(inquiry_content)
                            .updatedAt(new Timestamp(System.currentTimeMillis()))
                            .build();

                    customerServiceService.updateInquiry(inquiry_id, inquiry);
                    System.out.println();
                }
                case 4 -> {
                    System.out.print("삭제할 문의글 id를 입력하세요.: ");
                    int inquiry_id = scanner.nextInt();
                    customerServiceService.deleteInquiry(inquiry_id);
                    System.out.println();
                }
                case 5 -> {
                    isQuit = true;
                    System.out.println("이전 화면으로 돌아갑니다.");
                }
            }
        }
    }

}
