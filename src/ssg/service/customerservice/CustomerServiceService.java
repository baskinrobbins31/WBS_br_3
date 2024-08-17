package ssg.service.customerservice;

import ssg.dto.customerservice.Inquiry;

import java.util.List;

public class CustomerServiceService {

    CustomerServiceDAO customerServiceDAO;

    public List<Inquiry> getInquiries() {
        return customerServiceDAO.readAll();
    }

    public void createInquiry(Inquiry inquiry) {
        customerServiceDAO.create(inquiry);
    }

    public void updateInquiry(int id) {
        customerServiceDAO.update(id);
    }

    public void deleteInquiry(int id) {
        customerServiceDAO.delete(id);
    }
}
