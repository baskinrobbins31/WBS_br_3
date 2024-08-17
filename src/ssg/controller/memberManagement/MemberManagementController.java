package ssg.controller.memberManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.enums.UserType;
import ssg.library.script.MemberManagementScript;
import ssg.service.memberMgr.MemberManagementService;


public class MemberManagementController {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private final MemberManagementScript memberManagementScript = MemberManagementScript.getMEMBER_MANAGEMENT_SCRIPT_INSTANCE();
  private MemberManagementService memberMgrService = new MemberManagementService();
  private UserType userType;
  /** 회원권한별 회원관리메뉴 */
  public void memberMgrMenu(UserType userType) {
    this.userType = userType;

    switch (userType) {
      case ADMINISTRATOR, WH_ADMIN -> memberMgrMenuAdmin();
      case PRESIDENT_MEMBER -> memberMgrMenuPresident();
      case NORMAL_MEMBER -> memberMgrMenuNormal();
      default -> System.out.println("권한이 없습니다.");

    }

  }

  /** 회원관리 메뉴(관리자) */
  private void memberMgrMenuAdmin() {
    try {
      memberManagementScript.printAdminMemberMenu();
      int select = Integer.parseInt(br.readLine());
      switch (select) {
        case 1 -> memberMgrService.confirmMemberCreate();
        case 2 -> memberListMenu();
        case 3 -> memberMgrService.showInformation();
        case 4 -> memberMgrService.updateMember();
        case 5 -> memberMgrService.deleteMember();
        case 6 -> memberMgrService.updateAuthority();
        case 7 -> System.out.println("이전화면");
        default -> throw new NumberFormatException();
      }
      br.close();
    } catch (IOException | NumberFormatException e) {
      System.out.println("잘못된 입력입니다.");
    }
  }

  /** 회원관리 메뉴(일반직원) */
  private void memberMgrMenuNormal() {

    try {
      memberManagementScript.printEmplMemberMenu();
      int select = Integer.parseInt(br.readLine());
      switch (select) {
        case 1 -> memberMgrService.updateMember();
        case 2 -> memberMgrService.showInformation();
        case 3 -> System.out.println("이전화면");
        default -> throw new NumberFormatException();
      }
      br.close();
    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }

  /** 회원관리 메뉴(사장) */
  private void memberMgrMenuPresident() {

    try {
      memberManagementScript.printPresMemberMenu();
      int select = Integer.parseInt(br.readLine());
      switch (select) {
        case 1 -> memberMgrService.listBRN();
        case 2 -> memberMgrService.updateMember();
        case 3 -> memberMgrService.showInformation();
        case 4 -> memberMgrService.deleteMember();
        case 5 -> System.out.println("이전화면");
        default -> throw new NumberFormatException();
      }
      br.close();
    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }

  /** 회원관리 메뉴(배송기사) */
  private void memberMgrMenuDriver() {}


  /** 회원 조회 메뉴 */
  private void memberListMenu() {

    try {
      memberManagementScript.printListMenu();
      int select = Integer.parseInt(br.readLine());
      switch (select) {
        case 1 -> memberMgrService.listMember();
        case 2 -> memberMgrService.listAdmin();
        case 3 -> memberMgrService.listBRN();
        case 4 -> memberMgrMenu(userType);
      }
    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }
}

