package ssg.service.memberMgr;

import ssg.enums.UserType;

public interface MemberManagementInterface {
  void confirmMemberCreate();
  void listMember();
  void listAdmin(UserType type);
  void listBRN();
  void updateAuthority();
  void deleteMember();
  void findMember();
  void showInformation();
  void updateMember();


}
