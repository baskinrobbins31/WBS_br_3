package ssg.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.controller.customerservice.CustomerServiceController;
import ssg.controller.login.LoginController;
import ssg.controller.memberManagement.MemberManagementController;
import ssg.controller.outbound.OutboundController;
import ssg.library.script.LoginScript;

public class MainController {

  LoginScript loginScript = LoginScript.getLoginScriptInstance();
  CustomerServiceController customerServiceController = new CustomerServiceController();
  MemberManagementController memberManagementController = new MemberManagementController();
  OutboundController outboundController = new OutboundController();

  /** 사용자 로그인 후 뜨는 전체 메뉴 */
  public void mainControllerMenu() {
    boolean isOn = true;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      while (isOn) {
        try  {
          loginScript.printMainMenu();
          int select = Integer.parseInt(br.readLine());
          switch (select) {
            case 1 -> {
              System.out.println("회원관리");
              memberManagementController.memberManagementMenu(Main.loginOnMember.getUserType());
            }
            case 2 -> {
              System.out.println("상품관리");
            }
            case 3 -> {
              System.out.println("입고관리");
            }
            case 4 -> {
              System.out.println("재고관리");
            }
            case 5 -> {
              outboundController.outboundMenuSelect(Main.loginOnMember.getUserType());
            }
            case 6 -> {
              System.out.println("창고관리");
            }
            case 7 -> {
              customerServiceController.startMenu();
            }
            case 8 -> {
              System.out.println("로그아웃");
              Main.loginOnMember = null;
              isOn = false;
            }
            default -> throw new NumberFormatException();
          }
        } catch (NumberFormatException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
