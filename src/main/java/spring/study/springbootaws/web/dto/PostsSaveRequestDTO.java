package spring.study.springbootaws.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.study.springbootaws.domain.posts.Posts;

/*
    Entity 클래스를 Request/Response 클래스로 사용해서는 안 됩니다.
    Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스입니다.
    Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경됩니다.
    화면 변경은 아주 사소한 기능 변경인데,
    이를 위해 테이블과 연결된 Entity 클래스를 변경하는 것은 너무 큰 변경입니다.
 */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDTO {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
