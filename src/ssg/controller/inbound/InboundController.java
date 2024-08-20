package ssg.controller.inbound;

import java.io.InputStreamReader;
import java.sql.SQLException;
import ssg.dao.product.ProductDAO;
import ssg.dto.inbound.Inbound;
import ssg.dto.product.Product;
import ssg.dto.warehouse.Warehouse;
import ssg.library.script.Script;
import ssg.service.inbound.InboundService;
import ssg.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import ssg.service.warehouse.WarehouseService;

public class InboundController {

  private final InboundService inboundService;
  private final Script script;
  private final BufferedReader br;

  public InboundController(InboundService inboundService, Script script, BufferedReader br) {
    this.inboundService = inboundService;
    this.script = script;
    this.br = br;
  }

  public void processAdminInbound() {
    try {
      while (true) {
        script.printAdminInboundMenu();

        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
          case 1 -> approveInbound(br); //입고요청 승인
          case 2 -> updateInbound(br);  //입고요청 수정
          case 3 -> rejectInbound(br);  //입고요청 취소
          case 4 -> approvedInboundList();  //입고지시서 조회
          case 5 -> viewAdminInboundRequestList(); //입고요청리스트 조회
          case 6 -> viewAdminApprovedInboundList(br); //입고현황 조회
          case 7 -> {
            System.out.println("이전 작업으로 돌아갑니다.");
            return; //이전화면
          }
          default -> System.out.println("잘못된 선택입니다.");
        }
      }
    } catch (IOException e) {
      System.err.println("입출력 오류: " + e.getMessage());
    }
  }

  // 회원 입고 요청 처리
  public void processMemberInbound() {
    try {
      while (true) {
        script.printMemberInboundMenu();
        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
          case 1 -> requestInbound(br); // 입고 요청
          case 2 -> updateInbound(br);  // 입고 요청 수정
          case 3 -> rejectInbound(br);  // 입고 요청 취소
          case 4 -> viewMemberInboundRequestList(Main.loginOnMember.getId()); // 입고 요청 리스트 조회
          case 5 -> viewMemberApprovedInboundList(br); // 입고 현황 조회
          case 6 -> {
            System.out.println("이전 작업으로 돌아갑니다.");
            return;
          }
          default -> System.out.println("잘못된 선택입니다.");
        }
      }
    } catch (IOException e) {
      System.err.println("입출력 오류: " + e.getMessage());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  // 입고 요청 메서드
  private void requestInbound(BufferedReader br) throws IOException, SQLException {
    System.out.println("새로운 입고 요청을 생성합니다.");

    System.out.print("등록할 상품 키워드를 입력하세요: ");
    String keyword = br.readLine();
    List<Product> products = inboundService.findProductsByName(keyword);

    if (products.isEmpty()) {
      System.out.println("해당 키워드로 조회된 상품이 존재하지 않습니다.");
      return;
    }

    System.out.println("조회된 상품 목록:");
    products.forEach(product -> System.out.println(
        "상품 ID: " + product.getProductId() + ", 상품명: " + product.getProductName()));

    System.out.print("등록할 상품 ID를 입력하세요: ");
    int productId = Integer.parseInt(br.readLine());

    System.out.print("입고할 수량을 입력하세요: ");
    int productAmount = Integer.parseInt(br.readLine());

    System.out.println("대여한 창고 목록을 조회합니다.");
    List<Warehouse> warehouses = inboundService.getWarehousesByMember(Main.loginOnMember.getId());
    warehouses.forEach(warehouse -> System.out.println(
        "창고 ID: " + warehouse.getWId() + "\t\t창고명: " + warehouse.getWName()));

    System.out.print("입고할 창고 ID를 입력하세요: ");
    int wssId = Integer.parseInt(br.readLine());

    System.out.print("입고 예정일자를 입력하세요 (yyyy-MM-dd): ");
    LocalDateTime inboundExpectedDate = LocalDateTime.parse(br.readLine() + "T00:00:00");

    Inbound inbound = new Inbound();
    inbound.setProductName(inbound.getProductName());
    inbound.setUserId(Main.loginOnMember.getId());
    inbound.setWssId(wssId);
    inbound.setProductAmount(productAmount);
    inbound.setInboundExpectedDatetime(inboundExpectedDate);

    inboundService.createInboundRequest(inbound);
  }

  private void approveInbound(BufferedReader br) throws IOException {
    System.out.print("승인할 입고 요청 ID를 입력하세요: ");
    int inboundId = Integer.parseInt(br.readLine());
    inboundService.approveInboundRequest(inboundId);
  }

  private void viewAdminInboundRequestList() {
    List<Inbound> inboundList = inboundService.getAdminInboundRequest();
    if (inboundList.isEmpty()) {
      System.out.println("조회된 입고 요청이 없습니다.");
    } else {
      inboundList.forEach(inbound -> System.out.println(
          "입고 ID: " + inbound.getInboundId() +
              "\t\t상품 ID: " + inbound.getProductName() +
              "\t\t상태: " + inbound.getInboundStatus() +
              "\t\t요청 시간: " + inbound.getInboundRequestDatetime() +
              "\t\t승인 시간: " + (inbound.getInboundApprovalDatetime() != null
              ? inbound.getInboundApprovalDatetime() : "승인되지 않음") +
              "\t\t예상 입고 시간: " + inbound.getInboundExpectedDatetime()
      ));
    }
  }

  // 입고 요청 리스트 조회
  private void viewMemberInboundRequestList(int userId) {
    List<Inbound> inboundList = inboundService.getMemberInboundRequests(userId);
    if (inboundList.isEmpty()) {
      System.out.println("조회된 입고 요청이 없습니다.");
    } else {
      inboundList.forEach(inbound -> System.out.println(
          "입고 ID: " + inbound.getInboundId() +
              "\t\t상태: " + inbound.getInboundStatus().getValue() +
              "\t\t요청 시간: " + inbound.getInboundRequestDatetime() +
              "\t\t예상 입고 시간: " + inbound.getInboundExpectedDatetime()));
    }
  }

  private void viewAdminApprovedInboundList(BufferedReader br) throws IOException {
    System.out.println("관리자의 조회");
    System.out.println("조회 방식을 선택하세요");
    String view = "1.전체 조회\t\t2.기간별 조회\t\t3.월별 조회\t\t4.돌아가기";
    System.out.println(view);
    script.printInputNumber();

    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1 -> viewAdminAllApprovedInboundList();
      case 2 -> viewAdminApprovedInboundListByPeriod(br);
      case 3 -> viewAdminApprovedInboundListByMonth(br);
      case 4 -> {
        System.out.println("이전 작업으로 되돌아갑니다.");
        return;
      }
      default -> System.out.println("잘못된 선택입니다.");
    }
  }

  // 입고 현황 조회
  private void viewMemberApprovedInboundList(BufferedReader br) throws IOException {
    System.out.println("조회 방식을 선택하세요: 1. 전체 조회\t\t2. 기간별 조회\t\t3. 월별 조회\t\t4. 돌아가기");
    int choice = Integer.parseInt(br.readLine());

    switch (choice) {
      case 1 -> viewMemberAllApprovedInboundList();
      case 2 -> viewMemberApprovedInboundListByPeriod(br);
      case 3 -> viewMemberApprovedInboundListByMonth(br);
      case 4 -> {
        System.out.println("이전 작업으로 되돌아갑니다.");
        return;
      }
      default -> System.out.println("잘못된 선택입니다.");
    }
  }

  private void viewAdminAllApprovedInboundList() {
    List<Inbound> approvedList = inboundService.getAllApprovedInboundList();
    approvedList.forEach(inbound -> System.out.println(
        "입고 ID: " + inbound.getInboundId() + "\t\t상태: " + inbound.getInboundStatus()));
  }

  private void viewAdminApprovedInboundListByPeriod(BufferedReader br) throws IOException {
    System.out.print("시작 날짜를 입력하세요 (yyyy-MM-dd): ");
    LocalDateTime start = LocalDateTime.parse(br.readLine() + "T00:00:00");
    System.out.print("종료 날짜를 입력하세요 (yyyy-MM-dd): ");
    LocalDateTime end = LocalDateTime.parse(br.readLine() + "T23:59:59");
    List<Inbound> periodList = inboundService.getApprovedInboundListByPeriod(start, end);
    periodList.forEach(inbound -> System.out.println(
        "입고 ID: " + inbound.getInboundId() + "\t\t상태: " + inbound.getInboundStatus()));
  }

  private void viewAdminApprovedInboundListByMonth(BufferedReader br) throws IOException {
    System.out.print("조회할 연도를 입력하세요 (yyyy): ");
    int year = Integer.parseInt(br.readLine());
    System.out.print("조회할 월을 입력하세요 (MM): ");
    int month = Integer.parseInt(br.readLine());
    List<Inbound> monthList = inboundService.getApprovedInboundListByMonth(month, year);
    monthList.forEach(inbound -> System.out.println(
        "입고 ID: " + inbound.getInboundId() + "\t\t상태: " + inbound.getInboundStatus()));
  }


  private void viewMemberAllApprovedInboundList() {
    List<Inbound> approvedList = inboundService.getAllApprovedInboundList();
    approvedList.forEach(inbound -> System.out.println(
        "입고 ID: " + inbound.getInboundId() + "\t\t상태: " + inbound.getInboundStatus().getValue()));
  }

  private void viewMemberApprovedInboundListByPeriod(BufferedReader br) throws IOException {
    System.out.print("시작 날짜를 입력하세요 (yyyy-MM-dd): ");
    LocalDateTime start = LocalDateTime.parse(br.readLine() + "T00:00:00");
    System.out.print("종료 날짜를 입력하세요 (yyyy-MM-dd): ");
    LocalDateTime end = LocalDateTime.parse(br.readLine() + "T23:59:59");
    List<Inbound> periodList = inboundService.getApprovedInboundListByPeriod(start, end);
    periodList.forEach(inbound -> System.out.println(
        "입고 ID: " + inbound.getInboundId() + "\t\t상태: " + inbound.getInboundStatus().getValue()));
  }

  private void viewMemberApprovedInboundListByMonth(BufferedReader br) throws IOException {
    System.out.print("조회할 연도를 입력하세요 (yyyy): ");
    int year = Integer.parseInt(br.readLine());
    System.out.print("조회할 월을 입력하세요 (MM): ");
    int month = Integer.parseInt(br.readLine());
    List<Inbound> monthList = inboundService.getApprovedInboundListByMonth(month, year);
    monthList.forEach(inbound -> System.out.println(
        "입고 ID: " + inbound.getInboundId() + "\t\t상태: " + inbound.getInboundStatus().getValue()));
  }

  // 입고 요청 수정
  private void updateInbound(BufferedReader br) throws IOException {
    System.out.print("수정할 입고 요청 ID를 입력하세요: ");
    int inboundId = Integer.parseInt(br.readLine());
    Inbound updatedInbound = new Inbound();
    System.out.print("수정할 상품 수량을 입력하세요: ");
    updatedInbound.setProductAmount(Integer.parseInt(br.readLine()));

    inboundService.updateInboundRequest(inboundId, updatedInbound);
  }

  private void approvedInboundList() {
    List<Inbound> approvedList = inboundService.getAllApprovedInboundList();
    approvedList.forEach(inbound -> System.out.println(
        "입고 ID: " + inbound.getInboundId() + "\t\t상태: " + inbound.getInboundStatus()));
  }

  // 입고 요청 거절
  private void rejectInbound(BufferedReader br) throws IOException {
    System.out.print("거절할 입고 요청 ID를 입력하세요: ");
    int inboundId = Integer.parseInt(br.readLine());

    inboundService.rejectInboundRequest(inboundId);
  }
}
