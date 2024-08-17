package ssg.service.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.Main;
import ssg.controller.memberMgr.MemberMgrController;
import ssg.dao.login.LoginDao;
import ssg.dto.Member;
import ssg.library.script.LoginScript;

public class LoginService implements LoginServiceInterface {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  LoginScript loginScript  = LoginScript.getLoginScriptInstance();
  private LoginDao loginDao = new LoginDao();

  /** login */
  @Override
  public void loginStart() {
    try {
      loginScript.printInputID();
      String userid = br.readLine();

      loginScript.printInputPassWord();
      String password = br.readLine();

      if(loginDao.read(userid, password) instanceof Member m) {
          System.out.println("로그인 성공");
          Main.loginOnMember = m;
          MemberMgrController memberMgrController = new MemberMgrController();
          memberMgrController.memberMgrMenu(Main.loginOnMember);
      }
      else {
        loginScript.printUnknownMember();
      }
      br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** id 찾기 */
  @Override
  public void findID() {

    try {
      br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 비밀번호 찾기 */
  @Override
  public void findPassWord() {

    try {
      br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** 회원 등록 */
    @Override
  public void createMember() {
      try {
        System.out.print("id를 입력하세요 : ");
        int id = Integer.parseInt(br.readLine());

        loginScript.printInputID();
        String userid = br.readLine();

        loginScript.printInputPassWord();
        String passWord = br.readLine();

        System.out.print("이름을 입력하세요. : ");
        String name = br.readLine();

        System.out.print("전화번호를 입력하세요. : ");
        String phone = br.readLine();

        System.out.print("주소를 입력하세요 : ");
        String address = br.readLine();

        System.out.println("email을 입력하세요. : ");
        String email = br.readLine();

        Member createMember = new Member(id, userid, passWord, name, phone, address, email);
        loginDao.create(createMember);

        br.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
}
