package ssg.controller.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.library.script.LoginScript;
import ssg.service.login.LoginService;

public class LoginController {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private final LoginScript loginScript = LoginScript.getLoginScriptInstance();
  private LoginService loginService = new LoginService();


  /** 프로그램 실행시 처음 시작하는 메뉴 */
  public void startMenu() {
    boolean isOn = true;
    while(isOn) {
      try {

        loginScript.printStartMenu();
        int select = Integer.parseInt(br.readLine());

        switch (select) {
          case 1 -> {
            loginService.createMember();
          }
          case 2 -> {
            loginService.loginStart();
            isOn = false;
          }
          case 3 -> {
            System.out.println("운송장 조회");
          }
          case 4 -> {
            System.out.println("고객 센터");
          }
          case 5 ->{
            loginService.findID();
          }
          case 6 ->{
            loginService.findPassWord();
          }
          case 7 ->{
            System.out.println(" 프로그램 종료");
            isOn = false;}
          default -> throw new NumberFormatException();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (NumberFormatException e) {
        loginScript.printFaultInput();
      }
    }
  }


  ///** 로그인 선택시 뜨는 메뉴 */
 /* public void loginMenu() {
    boolean isOn = true;
    while (isOn) {
      try {
        //loginScript.printLoginAllMenu();
        int select = 0;
        select = Integer.parseInt(br.readLine());

        switch (select) {
          case 1 -> loginService.loginStart();
          case 2 -> loginService.findID();
          case 3 -> loginService.findPassWord();
          case 4 -> loginService.createMember();
          case 5 -> {startMenu(); isOn =false;}
          case 99 -> loginService.createAdmin();
          default -> throw new NumberFormatException();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (NumberFormatException e) {
        loginScript.printFaultInput();
      }
    }
  }*/


}
