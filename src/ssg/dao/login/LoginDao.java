package ssg.dao.login;

import ssg.dto.Member;
import ssg.library.dbio.AbstractDBIO;

public class LoginDao extends AbstractDBIO {

  private StringBuilder sb = new StringBuilder();

  @Override
  public void create(Object o) {

    if(o instanceof Member m) {
      sb.append("INSERT INTO  member(id, userid, password, userName, phone_number, address, email) VALUES ( '").
          append(m.getId()).append("', '").append(m.getUserid()).append("', '").append(m.getPassWord()).append("', '")
          .append(m.getUserName()).append("', '").append(m.getPhoneNumber()).append("', '").
          append(m.getAddress()).append("', '").append(m.getEmail()).append("' )");
      String qurey = sb.toString();
      System.out.println(qurey);
      super.excute(qurey);
      super.close();
    }

  }

  @Override
  public Object read() {
    return null;
  }

  @Override
  public void update(Object o) {

  }

  @Override
  public void delete(Object o) {

  }
}
