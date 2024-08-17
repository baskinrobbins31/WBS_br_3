package ssg.dto.customerservice;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry {
    private int id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Builder
    Inquiry(int id, String title, String content, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = updatedAt;
    }
}
