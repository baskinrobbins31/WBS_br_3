package ssg.dao.customerservice;

import ssg.dto.customerservice.Notice;
import ssg.library.dbio.AbstractDBIO2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO extends AbstractDBIO2<Notice> {
    @Override
    public void create(Notice notice) {

        String query = "INSERT INTO notice (title, content, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = super.getConnection().prepareStatement(query);

            pstmt.setString(1, notice.getTitle());
            pstmt.setString(2, notice.getContent());
            pstmt.setTimestamp(3, notice.getCreatedAt());
            pstmt.setTimestamp(4, notice.getUpdatedAt());
            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                System.out.println("공지글이 등록되었습니다.");
                pstmt.close();
                super.getConnection().close();
            } else {
                System.out.println("공지글이 등록되지 않았습니다.");
            }


        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public List<Notice> readAll() {
        String query = "SELECT * FROM notice";
        List<Notice> inquiries = new ArrayList<>();
        try {
            PreparedStatement pstmt = super.getConnection().prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 공지글이 없습니다.");
            } else {
                while (rs.next()) {
                    Notice notice = Notice.builder()
                            .id(rs.getInt(1))
                            .title(rs.getString(2))
                            .content(rs.getString(3))
                            .createdAt(rs.getTimestamp(4))
                            .updatedAt(rs.getTimestamp(5))
                            .build();
                    inquiries.add(notice);
                }
            }

            rs.close();
            pstmt.close();
            super.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return inquiries;
    }
    public void update(int id, Notice notice) {
        String query = "UPDATE notice SET title=?, content=?, updated_at=? WHERE id=?";

        try {
            Connection connection = getConnection();

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, notice.getTitle());
            pstmt.setString(2, notice.getContent());
            pstmt.setTimestamp(3, notice.getUpdatedAt());
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();

            if (rows == 1) {
                System.out.println("공지글이 수정되었습니다.");
            } else {
                System.out.println("공지글이 수정되지 않았습니다.");
            }

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM notice WHERE id=?";

        try {
            Connection connection = getConnection();

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();

            if (rows == 1) {
                System.out.println("공지글이 삭제되었습니다.");
            } else {
                System.out.println("공지글이 삭제되지 않았습니다.");
            }

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
