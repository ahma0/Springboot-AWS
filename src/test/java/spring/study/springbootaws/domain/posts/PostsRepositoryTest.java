package spring.study.springbootaws.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest     //별다른 설정 없이 @SpringBootTest를 사용할 경우 H2 데이터베이스를 자동으로 실행해줌
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(           //id 값이 있다면 update가, 없다면 insert 쿼리가 실행됩니다.
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("test12@gmail.com")
                        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);

    }
}
