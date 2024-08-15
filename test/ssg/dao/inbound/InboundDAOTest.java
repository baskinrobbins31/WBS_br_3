package ssg.dao.inbound;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InboundDAOTest {

  @Test
  void multiply() {

    InboundDAO dao = new InboundDAO();

    assertEquals(dao.multiply(10,20), 30);
  }

}