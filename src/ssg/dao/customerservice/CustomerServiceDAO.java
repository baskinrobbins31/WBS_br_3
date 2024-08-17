package ssg.dao.customerservice;

import ssg.dto.customerservice.Inquiry;
import ssg.library.dbio.AbstractDBIO2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceDAO extends AbstractDBIO2<Inquiry> {
    @Override
    public void create(Inquiry inquiry) {

        String query = "INSERT INTO inquiry (title, content, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = super.getConnection().prepareStatement(query);

            pstmt.setString(1, inquiry.getTitle());
            pstmt.setString(2, inquiry.getContent());
            pstmt.setTimestamp(3, inquiry.getCreatedAt());
            pstmt.setTimestamp(4, inquiry.getUpdatedAt());
            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                System.out.println("문의글이 등록되었습니다.");
                pstmt.close();
                super.getConnection().close();
            } else {
                System.out.println("문의글이 등록되지 않았습니다.");
            }


        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public List<Inquiry> readAll() {
        return super.readAll();
    }
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }
}
