package ssg.controller.customerservice;

import ssg.library.script.CustomerServiceScript;

import java.util.Scanner;

public class CustomerServiceController {
    CustomerServiceScript customerServiceScript = new CustomerServiceScript();

    public void startMenu() {
        customerServiceScript.printMenu();
        Scanner scanner = new Scanner(System.in);
        boolean isQuit = false;
        while (!isQuit) {
            System.out.print("원하는 메뉴를 입력하세요.: ");
            int number = scanner.nextInt();
            switch (number) {
                case 1 -> {
                    customerServiceScript.printNoticeMenu();
                    System.out.println("--공지사항 메뉴입니다.--");

                }
                case 2 -> {
                    customerServiceScript.printInquiryMenu();
                    System.out.println("--문의글 메뉴입니다.--");
                    System.out.print("원하는 메뉴를 입력하세요.: ");

                }
                case 3 -> {
                    isQuit = true;
                    System.out.println("이전 화면으로 돌아갑니다.");
                }
            }
        }

    }

    private void noticeMenu(Scanner scanner) {
        boolean isQuit = false;
        while (!isQuit) {
            System.out.print("원하는 메뉴를 입력하세요.: ");
            int menu = scanner.nextInt();
            switch (menu) {
                case 1 -> {
                    customerServiceService.getInquiries();
                }
                case 2 -> {
                    customerServiceService.createInquiry();
                }
                case 3 -> {
                    customerServiceService.updateInquiry();
                }
                case 4 -> {
                    customerServiceService.deleteInquiry();
                }
                case 5 -> {
                    isQuit = true;
                    System.out.println("이전 화면으로 돌아갑니다.");
                }
            }
        }
    }

}
