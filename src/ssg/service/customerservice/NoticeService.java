package ssg.service.customerservice;

import ssg.dao.customerservice.NoticeDAO;
import ssg.dto.customerservice.Notice;

import java.util.List;

public class NoticeService {

    NoticeDAO noticeDAO = new NoticeDAO();

    public List<Notice> getNotices() {
        return noticeDAO.readAll();
    }

    public void createNotice(Notice inquiry) {
        noticeDAO.create(inquiry);
    }

    public void updateNotice(int id, Notice notice) {
        noticeDAO.update(id, notice);
    }

    public void deleteNotice(int id) {
        noticeDAO.delete(id);
    }
}
