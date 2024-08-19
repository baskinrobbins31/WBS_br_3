package ssg.controller.inbound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.library.script.Script;
import ssg.service.inbound.InboundService;

public class InboundController {

  private InboundService inboundService;
  private Script script;

  public InboundController(InboundService inboundService, Script script) {
    this.inboundService = inboundService;
    this.script = script;
  }

  public void processAdminInbound() {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        script.printAdminInboundMenu();

        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
          case 1 -> approveInbound();
          case 2 -> updateInbound();
          case 3 -> rejectInbound();
          case 4 -> approvedInboundList();
          case 5 -> viewAdminInboundRequestList();
          case 6 -> viewAdminApprovedInboundList(br);
          case 7 -> {
            System.out.println("이전 작업으로 돌아갑니다.");
            return;
          }
        }
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  } //end

  public void processMemberInbound() {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        script.printMemberInboundMenu();

        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
          case 1 -> requestInbound();
          case 2 -> updateInbound();
          case 3 -> rejectInbound();
          case 4 -> viewMemberInboundRequestList();
          case 5 -> viewMemberApprovedInboundList(br);
          case 6 -> {
            System.out.println("이전 작업으로 돌아갑니다.");
            return;
          }
        }
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  } //end

  //입고요청
  private void requestInbound() throws IOException {
    System.out.println("요청이에요~");
  }

  //입고요청수정
  private void updateInbound() throws IOException {
    System.out.println("수정이에요~");
  }

  //입고요청취소
  private void rejectInbound() throws IOException {
    System.out.println("거절입니다~~");
  }

  //입고요청승인
  private void approveInbound() throws IOException {
    System.out.println("승인이요!");
  }

  private void approvedInboundList() throws IOException {
    System.out.println("입고지시서조회");
  }

  private void viewAdminInboundRequestList() throws IOException {
    System.out.println("입고요청서리스트");
  }

  private void viewMemberInboundRequestList() throws IOException {
    System.out.println("입고요청서리스트");
  }

  private void viewAdminApprovedInboundList(BufferedReader br) throws IOException {
    System.out.println("관리자의 조회");
    System.out.println("조회 방식을 선택하세요");
    String view = "========================="
        + "1.전체 조회\t"
        + "2.기간별 조회\t"
        + "3.월별 조회\t"
        + "4.돌아가기";
    System.out.println(view);
    script.printInputNumber();

    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1 -> viewAdminAllApprovedInboundList();
      case 2 -> viewAdminApprovedInboundListByPeriod();
      case 3 -> viewAdminApprovedInboundListByMonth();
      case 4 -> {
        System.out.println("이전 작업으로 되돌아갑니다.");
        return;
      }
      default -> System.out.println("잘못된 선택입니다.");
    }

  }

  private void viewMemberApprovedInboundList(BufferedReader br) throws IOException {
    System.out.println("사업자의 조회");
    System.out.println("조회 방식을 선택하세요");
    String view = "===================================\n"
        + "1.전체 조회\t"
        + "2.기간별 조회\t"
        + "3.월별 조회\t"
        + "4.돌아가기";
    System.out.println(view);
    script.printInputNumber();

    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1 -> viewMemberAllApprovedInboundList();
      case 2 -> viewMemberApprovedInboundListByPeriod();
      case 3 -> viewMemberApprovedInboundListByMonth();
      case 4 -> {
        System.out.println("이전 작업으로 되돌아갑니다.");
        return;
      }
      default -> System.out.println("잘못된 선택입니다.");
    }
  }

  //관리자 전체 입고승인조회
  private void viewAdminAllApprovedInboundList() {
    System.out.println("관리자 전체 입고승인조회");

  }

  //사업자 전체 입고승인조회
  private void viewMemberAllApprovedInboundList() {
    System.out.println("사업자 전체 입고승인조회");

  }

  //관리자 기간별 입고승인조회
  private void viewAdminApprovedInboundListByPeriod() {
    System.out.println("관리자 기간별 입고승인조회");

  }

  //사업자 기간별 입고승인조회
  private void viewMemberApprovedInboundListByPeriod() {
    System.out.println("사업자 기간별 입고승인조회");

  }

  //관리자 월별 입고승인조회
  private void viewAdminApprovedInboundListByMonth() {
    System.out.println("관리자 월별 입고승인조회");

  }

  //사업자 월별 입고승인조회
  private void viewMemberApprovedInboundListByMonth() {
    System.out.println("사업자 월별 입고승인조회");

  }

}
