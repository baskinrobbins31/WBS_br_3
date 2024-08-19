package ssg.service.outbound;

import java.util.ArrayList;
import java.util.List;
import ssg.dao.outbound.OutboundDao;
import ssg.dto.Outbound;
import ssg.enums.OutboundState;

public class OutboundService {

  private OutboundDao outboundDao = new OutboundDao();
  private StringBuilder sb = new StringBuilder();

  /** 출고 요청 승인 */
  public void okOutboundRequest(int id) {


  }

  /** 출고 요청 */
  public void outboundRequest() {}


  /** 출고 지시서, 리스트 조회 */
  public void outboundList(OutboundState type) {
    List<Outbound> outboundsList =  outboundDao.read(type);

    for(Outbound  o: outboundsList) {

    }



  }

}
