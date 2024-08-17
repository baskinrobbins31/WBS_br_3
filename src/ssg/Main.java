package ssg;

import ssg.controller.login.LoginController;
import ssg.dto.Member;

public class Main {

  public static Member loginOnMember;

  public static void main(String[] args) {

    LoginController loginController = new LoginController();

    loginController.startMenu();


  }
}
