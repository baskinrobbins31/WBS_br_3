package ssg.service.login;

import java.io.BufferedReader;

public interface LoginServiceInterface {
  public void loginStart(BufferedReader br);
  public void findID(BufferedReader br);
  public void findPassWord(BufferedReader br);
  public void createMember(BufferedReader br);

}
