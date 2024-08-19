package ssg.service.customerservice;

import ssg.dao.customerservice.InquiryDAO;
import ssg.dto.customerservice.Inquiry;

import java.util.List;

public class InquiryService {

    InquiryDAO customerServiceDAO = new InquiryDAO();

    public List<Inquiry> getInquiries() {
        return customerServiceDAO.readAll();
    }

    public void createInquiry(Inquiry inquiry) {
        customerServiceDAO.create(inquiry);
    }

    public void updateInquiry(int id, Inquiry inquiry) {
        customerServiceDAO.update(id, inquiry);
    }

    public void deleteInquiry(int id) {
        customerServiceDAO.delete(id);
    }
}
