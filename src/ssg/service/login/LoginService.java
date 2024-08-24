package ssg.service.login;

import static ssg.enums.UserType.ADMINISTRATOR;
import static ssg.enums.UserType.NORMAL_MEMBER;
import static ssg.enums.UserType.PRESIDENT_MEMBER;
import static ssg.enums.UserType.WH_ADMIN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.controller.UserTypeControllerProcessor;
import ssg.dao.login.LoginDao;
import ssg.dto.Member;
import ssg.enums.UserState;
import ssg.enums.UserType;
import ssg.library.script.LoginScript;

public class LoginService implements LoginServiceInterface {
  LoginScript loginScript  = LoginScript.getLoginScriptInstance();
  private LoginDao loginDao = new LoginDao();
  private int UserType;

  /** 사용자 login */
  @Override
  public void loginStart(BufferedReader br) {
    try {
      System.out.println("--로그인--");
      loginScript.printInputUserID();
      String userid = br.readLine();

      loginScript.printInputPassWord();
      String password = br.readLine();

      if(loginDao.read(userid, password) instanceof Member m) {
        if(m.getMemberConfirm() == UserState.ACCESS_OK) {
          System.out.println("로그인 성공");
          Main.loginOnMember = m;
          UserTypeControllerProcessor userTypeCP = new UserTypeControllerProcessor();

          UserType userType = m.getUserType();

          switch (userType) {
            case ADMINISTRATOR -> {
              System.out.println("총관리자 권한으로 로그인되었습니다.");
              userTypeCP.viewAdminMenu();
            }
            case WH_ADMIN -> {
              System.out.println("창고관리자 권한으로 로그인되었습니다.");
              userTypeCP.viewWarehouseAdminMenu();
            }
            case PRESIDENT_MEMBER -> {
              System.out.println("사장 회원으로 로그인되었습니다.");
              userTypeCP.viewPresidentMenu();
            }
            case NORMAL_MEMBER -> {
              System.out.println("일반 회원으로 로그인되었습니다.");
              userTypeCP.viewPresidentMenu();
            }
            default -> throw new IllegalStateException("잘못된 값입니다.");
          }
        } else if (m.getMemberConfirm() == UserState.ACCESS_WAIT) {
          System.out.println("승인 대기중입니다.");
        }
      } else {
        loginScript.printUnknownMember();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 로그인id 찾기 */
  @Override
  public void findID(BufferedReader br) {

    try {
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
    } catch (IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }

  /** 비밀번호 찾기 */
  @Override
  public void findPassWord(BufferedReader br) {
    try {
      System.out.println("--비밀번호 찾기--");
      loginScript.printInputUserID();
      String userid = br.readLine();

      String findPass = loginDao.read(userid, 4);

      if (findPass == null) {
        loginScript.printNotFoundID();
      }
      else {
        System.out.println(userid + " 계정의 비밀번호는 : " + findPass);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 회원 등록 */
    @Override
  public void createMember(BufferedReader br) {
      try{
        System.out.println("--회원 등록--");

        loginScript.printInputUserID();
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

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    public void createAdmin() {
      loginDao.CreateAdmins();
    }
}
