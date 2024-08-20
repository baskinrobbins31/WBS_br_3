package ssg;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.controller.login.LoginController;
import ssg.controller.inbound.InboundController;
import ssg.controller.product.ProductController;
import ssg.dao.category.CategoryDAO;
import ssg.dao.inbound.InboundDAO;
import ssg.dao.product.ProductDAO;
import ssg.dto.Member;
import ssg.library.script.Script;
import ssg.service.inbound.InboundService;
import ssg.service.product.ProductService;
import ssg.service.warehouse.WarehouseService;

public class Main {

  public static Member loginOnMember; //로그인 후 유저 정보를 담고 있음
  public  static BufferedReader brInstance = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {

    /*LoginController loginController = new LoginController();
    loginController.startMenu();*/
//
    /*ProductController productController = new ProductController(new ProductService(new ProductDAO(), new CategoryDAO()));
    productController.processProducts();*/

    /*ProductController productController = new ProductController(new ProductService(new ProductDAO(), new CategoryDAO()));

    productController.processProducts();*/

    /*InboundController inboundController = new InboundController(new InboundService(new InboundDAO()),new Script());
    inboundController.processMemberInbound();*/



    //logincontroller.startMenu _> loginstart 분기 시점 -> maincontroller(Admin, WH, President, Normal)
    LoginController loginController = new LoginController();
    loginController.startMenu();

    brInstance.close();
  }
}
