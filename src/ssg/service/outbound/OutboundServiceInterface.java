package ssg.service.outbound;

import ssg.dto.outbound.OutboundRequest;
import ssg.enums.OutboundState;

public interface OutboundServiceInterface {
  void getOutboundRequestListAll(OutboundState state);
  void getOutboundListAll();
  void okOutboundRequest(String ids, OutboundState state);
  void createOutboundRequest(OutboundRequest request);
}
