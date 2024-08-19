package ssg.controller.outbound;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import ssg.enums.UserType;
import ssg.library.script.OutboundScript;

public class OutboundController {

  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private OutboundScript outboundScript = new OutboundScript();

  public void outboundMenuSelect(UserType type) {

    switch (type) {
      case ADMINISTRATOR, WH_ADMIN -> outboundMenuAdmin();
      case PRESIDENT_MEMBER, NORMAL_MEMBER -> outboundMenuMember();
    }
  }
  /** 출고관리 (관리자) */
  private void outboundMenuAdmin() {
    outboundScript.printAdminOutboundMenu();
  }
  /** 출고관리(회원) */
  private void outboundMenuMember() {
    outboundScript.printMemberOutboundMenu();
  }


  private void waybillMenuAdmin() {}


}
