package ssg.service.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ssg.library.script.LoginScript;

public class LoginService implements LoginServiceInterface {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  LoginScript loginScript  = LoginScript.getLoginScriptInstance();
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
        br.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
}
