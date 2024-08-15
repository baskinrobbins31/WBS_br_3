package ssg.service.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.dao.login.LoginDao;
import ssg.dto.Member;
import ssg.library.script.LoginScript;

public class LoginService implements LoginServiceInterface {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  LoginScript loginScript  = LoginScript.getLoginScriptInstance();
  private LoginDao loginDao = new LoginDao();
  @Override
  public void loginStart() {
    try {

      br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void findID() {

    try {
      br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void findPassWord() {

    try {
      br.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
    @Override
  public void createMember() {
      try {
        System.out.print("id를 입력하세요 : ");
        int id = Integer.parseInt(br.readLine());

        loginScript.printInputID();
        String userid = br.readLine();

        System.out.print("비밀번호를 입력하세요. : ");
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
