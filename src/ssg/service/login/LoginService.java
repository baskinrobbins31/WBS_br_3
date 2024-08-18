package ssg.service.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.controller.MainController;
import ssg.dao.login.LoginDao;
import ssg.dto.Member;
import ssg.library.script.LoginScript;

public class LoginService implements LoginServiceInterface {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  LoginScript loginScript  = LoginScript.getLoginScriptInstance();
  private LoginDao loginDao = new LoginDao();

  /** 사용자 login */
  @Override
  public void loginStart() {
    try {
      System.out.println("--로그인--");
      loginScript.printInputID();
      String userid = br.readLine();

      loginScript.printInputPassWord();
      String password = br.readLine();

      if(loginDao.read(userid, password) instanceof Member m) {
          System.out.println("로그인 성공");
          Main.loginOnMember = m;
        MainController allController = new MainController();
        allController.mainControllerMenu();
      }
      else {
        loginScript.printUnknownMember();
      }
      //br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 로그인id 찾기 */
  @Override
  public void findID() {

    try {
      System.out.println("--아이디 찾기--");
      loginScript.printFindIDMenu();
      int select = Integer.parseInt(br.readLine());
      String find;
      String word =null;
      switch (select) {
        case 1 -> {loginScript.printInputPhoneNumber();
                    find = br.readLine();
                    word = loginDao.read(find, 1);}
        case 2 -> {loginScript.printInputEmail();
                    find = br.readLine();
                    word = loginDao.read(find, 2);}
        case 3 -> {loginScript.printInputBRN();
                    find = br.readLine();
                    word = loginDao.read(find, 3);}
        default -> loginScript.printFaultInput();
      }

      if (word == null)
        loginScript.printNotFoundID();
      else {
        System.out.println("찾으시는 아이디는 " + word);
      }

      br.close();
    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }

  /** 비밀번호 찾기 */
  @Override
  public void findPassWord() {
    try {
      System.out.println("--비밀번호 찾기--");
      loginScript.printInputID();
      String userid = br.readLine();

      String findPass = loginDao.read(userid, 3);

      if (findPass == null) {
        loginScript.printNotFoundID();
      }
      else {
        System.out.println(userid + " 계정의 비밀번호는 : " + findPass);
      }


      br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 회원 등록 */
    @Override
  public void createMember() {
      try {
        System.out.println("--회원 등록--");

        loginScript.printInputID();
        String userid = br.readLine();

        loginScript.printInputPassWord();
        String passWord = br.readLine();

        loginScript.printInputName();
        String name = br.readLine();

        loginScript.printInputPhoneNumber();
        String phone = br.readLine();

        loginScript.printInputAddress();
        String address = br.readLine();

        loginScript.printInputEmail();
        String email = br.readLine();

        //Member createMember = new Member(id, userid, passWord, name, phone, address, email);
        Member createMember =Member.builder().userid(userid).passWord(passWord).userName(name).
            phoneNumber(phone).address(address).email(email).build();
        loginDao.create(createMember);

        br.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    public void createAdmin() {
      loginDao.CreateAdmins();
    }
}
