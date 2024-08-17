package ssg.dto.customerservice;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class Inquiry {
    private int id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
