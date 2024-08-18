package ssg.service.memberMgr;

import ssg.enums.UserType;

public interface MemberManagementInterface {
  void confirmMemberCreate(int id);
  void listMember();
  void listAdmin(UserType type);
  void listBRN(String brn);
  void updateAuthority();
  void deleteMember();
  void findMember();
  void showInformation();
  void updateMember();


}
