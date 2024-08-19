package ssg.controller.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ssg.Main;
import ssg.controller.customerservice.CustomerServiceController;
import ssg.library.script.LoginScript;
import ssg.service.login.LoginService;

public class LoginController {

  private final LoginScript loginScript = LoginScript.getLoginScriptInstance();
  private LoginService loginService = new LoginService();

  CustomerServiceController customerServiceController = new CustomerServiceController();


  /**
   * 프로그램 실행시 처음 시작하는 메뉴
   */
  public void startMenu() {

    boolean isOn = true;
    BufferedReader br = Main.brInstance;

    while (isOn) {
      try {
        loginScript.printStartMenu();
        int select = Integer.parseInt(br.readLine());

        switch (select) {
          case 1 -> loginService.createMember(br);
          case 2 -> loginService.loginStart(br);
          case 3 -> System.out.println("운송장 조회");
          case 4 -> System.out.println("고객센터");
          case 5 -> loginService.findID(br);
          case 6 -> loginService.findPassWord(br);
          case 7 -> {
            System.out.println("종료");
            isOn = false;
          }
          case 999 -> loginService.createAdmin();
          default -> System.out.println("잘못된 입력입니다.");
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (NumberFormatException e) {
        loginScript.printFaultInput();
      }
    }
  }
}


