package spring.study.springbootaws.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.study.springbootaws.domain.posts.Posts;
import spring.study.springbootaws.domain.posts.PostsRepository;
import spring.study.springbootaws.web.dto.PostsResponseDTO;
import spring.study.springbootaws.web.dto.PostsSaveRequestDTO;
import spring.study.springbootaws.web.dto.PostsUpdateRequestDTO;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO) {
        return postsRepository.save(requestDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDTO.getTitle(), requestDTO.getContent());

        return id;

    }

    public PostsResponseDTO findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDTO(entity);
    }
}
