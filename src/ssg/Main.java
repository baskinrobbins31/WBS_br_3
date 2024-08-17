package ssg;

import ssg.controller.login.LoginController;
import ssg.dto.Member;

public class Main {

  public static Member loginOnMember; //로그인 후 유저 정보를 담고 있음

  public static void main(String[] args) {

    LoginController loginController = new LoginController();

    loginController.startMenu();


  }
}
