package ssg.service.memberMgr;

import java.io.BufferedReader;
import ssg.enums.UserType;

public interface MemberManagementInterface {
  void confirmMemberCreate(int id);
  void listMember();
  void listAdmin(UserType type);
  void listBRN(String brn);
  void updateAuthority(BufferedReader br);
  void deleteMember(int id);
  void findMember();
  void showInformation();
  void updateMember(BufferedReader br);


}
