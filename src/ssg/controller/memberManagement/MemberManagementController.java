package ssg.controller.memberManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.enums.UserType;
import ssg.library.script.MemberManagementScript;
import ssg.service.memberMgr.MemberManagementService;


public class MemberManagementController {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private final MemberManagementScript memberManagementScript = MemberManagementScript.getMEMBER_MANAGEMENT_SCRIPT_INSTANCE();
  private MemberManagementService memberManagementService = new MemberManagementService();

  /** 회원권한별 회원관리메뉴 */
  public void memberManagementMenu(UserType userType) {

    switch (userType) {
      case ADMINISTRATOR, WH_ADMIN -> memberManegementMenuAdmin();
      case PRESIDENT_MEMBER -> memberManagementMenuPresident();
      case NORMAL_MEMBER -> memberManagementMenuNormal();
      default -> System.out.println("권한이 없습니다.");
    }
  }

  /** 회원관리 메뉴(관리자) */
  private void memberManegementMenuAdmin() {
    boolean isOn = true;
    while (isOn) {
      try {
        memberManagementScript.printAdminMemberMenu();
        int select = Integer.parseInt(br.readLine());
        switch (select) {
          case 1 -> confirmMember();
          case 2 -> memberListMenu();
          case 3 -> memberManagementService.showInformation();
          case 4 -> memberManagementService.updateMember();
          case 5 -> memberManagementService.deleteMember();
          case 6 -> memberManagementService.updateAuthority();
          case 7 -> {System.out.println("이전화면"); isOn = false;}
          default -> throw new NumberFormatException();
        }
        //br.close();
      } catch (IOException | NumberFormatException e) {
        System.out.println("잘못된 입력입니다.");
      }
    }
  }

  /** 회원관리 메뉴(일반직원) */
  private void memberManagementMenuNormal() {
    boolean isOn = true;
    while (isOn) {
      try {
        memberManagementScript.printEmplMemberMenu();
        int select = Integer.parseInt(br.readLine());
        switch (select) {
          case 1 -> memberManagementService.updateMember();
          case 2 -> memberManagementService.showInformation();
          case 3 -> {System.out.println("이전화면"); isOn = false;}
          default -> throw new NumberFormatException();
        }
        // br.close();
      } catch (IOException | NumberFormatException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /** 회원관리 메뉴(사장) */
  private void memberManagementMenuPresident() {
    boolean isOn = true;
    while (isOn) {
      try {
        memberManagementScript.printPresMemberMenu();
        int select = Integer.parseInt(br.readLine());
        switch (select) {
          case 1 -> memberManagementService.listBRN(Main.loginOnMember.getBRN());
          case 2 -> memberManagementService.updateMember();
          case 3 -> memberManagementService.showInformation();
          case 4 -> memberManagementService.deleteMember();
          case 5 -> {System.out.println("이전화면"); isOn = false;}
          default -> throw new NumberFormatException();
        }
        //br.close();
      } catch (IOException | NumberFormatException e) {
        throw new RuntimeException(e);
      }
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
        case 1 -> memberManagementService.listMember();
        case 2 -> memberTypeListMenu();
        case 3 -> memberBRNListMenu();
        case 4 -> memberManagementMenu(Main.loginOnMember.getUserType());
      }
    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }

  /** 관리자 조회시 enum 선택 */
  private void memberTypeListMenu() {

    try {
      memberManagementScript.printFindAdminMenu();
      int select = Integer.parseInt(br.readLine());
      switch (select) {
        case 1 -> memberManagementService.listAdmin(UserType.ADMINISTRATOR);
        case 2 -> memberManagementService.listAdmin(UserType.WH_ADMIN);
        case 3 -> memberManagementService.listAdmin(UserType.PRESIDENT_MEMBER);
        case 4 -> memberManagementService.listAdmin(UserType.NORMAL_MEMBER);
        case 5 -> memberListMenu();
        default -> throw new NumberFormatException();
      }

    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }

  /** 직원 조회(사장) */
  private void memberBRNListMenu() {
    try {
      System.out.println("--사업자번호 조회--");
      memberManagementScript.printInputBRN();
      String brn = br.readLine();
      memberManagementService.listBRN(brn);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void confirmMember() {
    boolean isOn = true;
    while (isOn) {
      try {
        System.out.println("1.승인 대기 회원 조회\t" + "2.회원 승인\t" + "3.전체 승인\t" + "4.이전화면");
        int select = Integer.parseInt(br.readLine());
        switch (select) {
          case 1 -> memberManagementService.confirmMemberList();
          case 2 -> {
            memberManagementScript.printInputID();
            int id = Integer.parseInt(br.readLine());
            memberManagementService.confirmMemberCreate(id);}
          case 3 -> memberManagementService.confirmMemberAll();
          case 4 -> { isOn = false;
          }
          default -> throw new NumberFormatException();
        }

      } catch (IOException | NumberFormatException e) {
        throw new RuntimeException(e);
      }
    }
  }

}

