package ssg.controller.memberMgr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import ssg.dto.Member;
import ssg.library.script.MemberMgrScript;
import ssg.service.memberMgr.MemberMgrService;


public class MemberMgrController {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private MemberMgrScript memberMgrScript = MemberMgrScript.getMemberMgrScriptInstance();
  private MemberMgrService memberMgrService = new MemberMgrService();

  public void memberMgrMenu(Member member) {

    switch (member.getUserType()) {
      case ADMINISTRATOR, WH_ADMIN -> memberMgrMenuAdmin();
      case PRESIDENT_MEMBER -> memberMgrMenuPresident();
      case NORMAL_MEMBER -> memberMgrMenuNormal();
      default -> System.out.println("권한이 없습니다.");

    }

  }

  private void memberMgrMenuAdmin() {
    memberMgrScript.printAdminMemberMenu();
  }

  private void memberMgrMenuNormal() {
    memberMgrScript.printEmplMemberMenu();
  }

  private void memberMgrMenuPresident() {
    memberMgrScript.printPresMemberMenu();
  }

  private void memberMgrMenuDriver() {}
}
