package spring.study.springbootaws.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.study.springbootaws.domain.posts.PostsRepository;
import spring.study.springbootaws.web.dto.PostsSaveRequestDTO;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO) {
        return postsRepository.save(requestDTO.toEntity()).getId();
    }
}
