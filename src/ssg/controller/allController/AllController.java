package ssg.controller.allController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.controller.login.LoginController;
import ssg.controller.memberManagement.MemberManagementController;
import ssg.library.script.LoginScript;

public class AllController {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  LoginScript loginScript = LoginScript.getLoginScriptInstance();


  /** 사용자 로그인 후 뜨는 전체 메뉴 */
  public void allControllerMenu() {
    try {
      loginScript.printMainMenu();
      int select = Integer.parseInt(br.readLine());
      switch (select){
        case 1-> {
          MemberManagementController memberManagementController = new MemberManagementController();
                  memberManagementController.memberMgrMenu(Main.loginOnMember.getUserType());}
        case 2 -> {
          System.out.println("입고관리");
        }
        case 3 -> {System.out.println("출고관리");}
        case 4 -> {System.out.println("재고관리");}
        case 5 -> {System.out.println("창고관리");}
        case 6 -> {System.out.println("고객센터");}
        case 7 -> {Main.loginOnMember = null;
          LoginController loginController = new LoginController();
          loginController.startMenu();
        }
        default -> throw new NumberFormatException();
      }
    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }
}
