package ssg;

import ssg.controller.MainController;
import ssg.controller.login.LoginController;
import ssg.controller.product.ProductController;
import ssg.dao.inbound.ProductDAO;
import ssg.dto.Member;
import ssg.service.ProductService;

public class Main {

  public static Member loginOnMember; //로그인 후 유저 정보를 담고 있음

  public static void main(String[] args) {
    MainController mainController = new MainController();

    // LoginController loginController = new LoginController();


    mainController.mainControllerMenu();
  }
}
