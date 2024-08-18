package ssg.dao.outbound;

import java.util.List;
import ssg.library.dbio.AbstractDBIO2;

public class OutboundDao extends AbstractDBIO2 {

  @Override
  protected void create(Object o) {
    super.create(o);
  }

  @Override
  protected Object read() {
    return super.read();
  }

  @Override
  protected List readAll() {
    return super.readAll();
  }

  @Override
  protected void update(int id, Object o) {
    super.update(id, o);
  }

  @Override
  protected void delete(int id) {
    super.delete(id);
  }
}
