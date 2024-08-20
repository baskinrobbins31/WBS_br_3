package ssg;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.controller.login.LoginController;
import ssg.dto.Member;


public class Main {

  public static Member loginOnMember; //로그인 후 유저 정보를 담고 있음
  public  static BufferedReader brInstance = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {


    //logincontroller.startMenu _> loginstart 분기 시점 -> maincontroller(Admin, WH, President, Normal)
    LoginController loginController = new LoginController();
    loginController.startMenu();

    brInstance.close();
  }
}
