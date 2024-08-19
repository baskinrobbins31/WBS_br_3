package ssg.service.inbound;

import java.sql.SQLException;
import ssg.dao.inbound.InboundDAO;
import ssg.dto.inbound.Inbound;
import ssg.dto.product.Product;
import ssg.dto.warehouse.Warehouse;
import ssg.enums.InboundStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InboundService {

  private final InboundDAO inboundDAO;

  public InboundService(InboundDAO inboundDAO) {
    this.inboundDAO = inboundDAO;
  }

  // 입고 요청 생성
  public void createInboundRequest(Inbound inbound) {
    inbound.setInboundStatus(InboundStatus.PENDING_REQUEST);
    inbound.setInboundRequestDatetime(LocalDateTime.now());
    inboundDAO.create(inbound);
    System.out.println("입고 요청이 생성되었습니다.");
  }

  // 입고 요청 승인
  public void approveInboundRequest(int inboundId) {
    Optional<Inbound> optionalInbound = inboundDAO.read(inboundId);
    if (optionalInbound.isPresent()) {
      Inbound inbound = optionalInbound.get();
      inbound.setInboundStatus(InboundStatus.APPROVED_REQUEST);
      inbound.setInboundApprovalDatetime(LocalDateTime.now());
      inboundDAO.update(inboundId, inbound);
      System.out.println("입고 요청이 승인되었습니다.");
    } else {
      System.out.println("입고 요청을 찾을 수 없습니다.");
    }
  }

  // 특정 사용자의 입고 요청 조회
  public List<Inbound> getMemberInboundRequests(int userId) {
    return inboundDAO.readAll().stream()
        .filter(inbound -> inbound.getUserId() == userId)
        .collect(Collectors.toList());
  }

  // 관리자의 모든 입고 요청 조회
  public List<Inbound> getAdminInboundRequest() {
    return inboundDAO.readAll();
  }

  // 승인된 입고 리스트 조회
  public List<Inbound> getAllApprovedInboundList() {
    return inboundDAO.readAll().stream()
        .filter(inbound -> inbound.getInboundStatus() == InboundStatus.APPROVED_REQUEST)
        .collect(Collectors.toList());
  }

  // 기간별 승인된 입고 리스트 조회
  public List<Inbound> getApprovedInboundListByPeriod(LocalDateTime start, LocalDateTime end) {
    return inboundDAO.readAll().stream()
        .filter(inbound -> inbound.getInboundStatus() == InboundStatus.APPROVED_REQUEST
            && inbound.getInboundApprovalDatetime() != null
            && !inbound.getInboundApprovalDatetime().isBefore(start)
            && !inbound.getInboundApprovalDatetime().isAfter(end))
        .collect(Collectors.toList());
  }

  // 월별 승인된 입고 리스트 조회
  public List<Inbound> getApprovedInboundListByMonth(int month, int year) {
    return inboundDAO.readAll().stream()
        .filter(inbound -> inbound.getInboundStatus() == InboundStatus.APPROVED_REQUEST
            && inbound.getInboundApprovalDatetime() != null
            && inbound.getInboundApprovalDatetime().getMonthValue() == month
            && inbound.getInboundApprovalDatetime().getYear() == year)
        .collect(Collectors.toList());
  }

  // 입고 요청 수정
  public void updateInboundRequest(int inboundId, Inbound updatedInbound) {
    updatedInbound.setInboundId(inboundId);
    inboundDAO.update(inboundId, updatedInbound);
    System.out.println("입고 요청이 수정되었습니다.");
  }

  // 입고 요청 거절
  public void rejectInboundRequest(int inboundId) {
    Optional<Inbound> optionalInbound = inboundDAO.read(inboundId);
    if (optionalInbound.isPresent()) {
      Inbound inbound = optionalInbound.get();
      inbound.setInboundStatus(InboundStatus.REJECTED_REQUEST);
      inboundDAO.update(inboundId, inbound);
      System.out.println("입고 요청이 거절되었습니다.");
    } else {
      System.out.println("입고 요청을 찾을 수 없습니다.");
    }
  }

  public List<Product> findProductsByName(String keyword) throws SQLException {
    if (keyword == null || keyword.isEmpty()) {
      throw new IllegalArgumentException("키워드 값이 없습니다.");
    }

    return inboundDAO.findProductsByName(keyword);
  }

  public List<Warehouse> getWarehousesByMember(int memberId) throws SQLException {
    return inboundDAO.findWarehousesByMemberId(memberId);
  }

  // 입고 요청 삭제
  public void deleteInboundRequest(int inboundId) {
    inboundDAO.delete(inboundId);
    System.out.println("입고 요청이 삭제되었습니다: ID = " + inboundId);
  }
}
