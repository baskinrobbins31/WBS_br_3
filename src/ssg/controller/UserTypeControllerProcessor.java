package ssg.controller;

import static ssg.Main.brInstance;
import static ssg.Main.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.controller.customerservice.CustomerServiceController;
import ssg.controller.inbound.InboundController;
import ssg.controller.memberManagement.MemberManagementController;
import ssg.controller.outbound.OutboundController;
import ssg.controller.product.ProductController;
import ssg.controller.warehouse.WarehouseController;
import ssg.dao.category.CategoryDAO;
import ssg.dao.inbound.InboundDAO;
import ssg.dao.product.ProductDAO;
import ssg.library.script.LoginScript;
import ssg.library.script.Script;
import ssg.service.inbound.InboundService;
import ssg.service.product.ProductService;

public class UserTypeControllerProcessor {

  LoginScript loginScript = LoginScript.getLoginScriptInstance();
  CustomerServiceController customerServiceController = new CustomerServiceController();
  MemberManagementController memberManagementController = new MemberManagementController();
  OutboundController outboundController = new OutboundController();
  WarehouseController warehouseController = new WarehouseController();
  InboundController inboundController
      = new InboundController(new InboundService(new InboundDAO()), new Script(), new BufferedReader(new InputStreamReader(System.in)));
  ProductController productController
      = new ProductController(new ProductService(new ProductDAO(),new CategoryDAO()), new BufferedReader(new InputStreamReader(System.in)));

  public void viewAdminMenu(){

    boolean isOn = true;
    while (isOn) {
      try {
        loginScript.printMainMenu();
        int select = Integer.parseInt(Main.brInstance.readLine());

        switch (select) {
          case 1 -> memberManagementController.memberManagementMenu(Main.loginOnMember.getUserType());
          case 2 -> productController.processProducts();
          case 3 -> inboundController.processAdminInbound();
          case 4 -> System.out.println("재고관리");
          case 5 -> outboundController.outboundMenuSelect(Main.loginOnMember.getUserType());
          case 6 -> System.out.println("창고관리");
          case 7 -> customerServiceController.startMenu();
          case 8 -> {
            System.out.println("로그아웃");
            Main.loginOnMember = null;
            isOn = false;
          }
          default -> throw new NumberFormatException();
        }
      } catch (IOException | NumberFormatException e) {
        loginScript.printFaultInput();
      }
    }

  }


  public void viewWarehouseAdminMenu(){
    boolean isOn = true;
    while (isOn) {
      try {
        loginScript.printMainMenu();
        int select = Integer.parseInt(Main.brInstance.readLine());
        //테스트를 위해 임의로 번호할당했습니다.
        switch (select) {
          case 1 -> memberManagementController.memberManagementMenu(Main.loginOnMember.getUserType());
          case 2 -> System.out.println("2.");
          case 3 -> System.out.println("3.");
          case 4 -> System.out.println("4.");
          case 5 -> outboundController.outboundMenuSelect(Main.loginOnMember.getUserType());
          case 6 -> System.out.println("창고관리");
          case 7 -> customerServiceController.startMenu();
          case 8 -> {
            System.out.println("로그아웃");
            Main.loginOnMember = null;
            isOn = false;
          }
          default -> throw new NumberFormatException();
        }
      } catch (IOException | NumberFormatException e) {
        loginScript.printFaultInput();
      }
    }
  }

  public void viewPresidentMenu(){
    boolean isOn = true;
    while (isOn) {
      try {
        loginScript.printMainMenu();
        int select = Integer.parseInt(Main.brInstance.readLine());
        //테스트를 위해 임의로 번호할당했습니다.
        switch (select) {
          case 1 -> memberManagementController.memberManagementMenu(Main.loginOnMember.getUserType());
          case 2 -> System.out.println("2.");
          case 3 -> inboundController.processMemberInbound();
          case 4 -> System.out.println("4.");
          case 5 -> outboundController.outboundMenuSelect(Main.loginOnMember.getUserType());
          case 6 -> System.out.println("창고관리");
          case 7 -> customerServiceController.startMenu();
          case 8 -> {
            System.out.println("로그아웃");
            Main.loginOnMember = null;
            isOn = false;
          }
          default -> throw new NumberFormatException();
        }
      } catch (IOException | NumberFormatException e) {
        loginScript.printFaultInput();
      }
    }
  }

  public void viewMemberMenu(){

  }

}
