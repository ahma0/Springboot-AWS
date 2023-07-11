package spring.study.springbootaws.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.study.springbootaws.domain.posts.Posts;
import spring.study.springbootaws.domain.posts.PostsRepository;
import spring.study.springbootaws.web.dto.PostsListResponseDTO;
import spring.study.springbootaws.web.dto.PostsResponseDTO;
import spring.study.springbootaws.web.dto.PostsSaveRequestDTO;
import spring.study.springbootaws.web.dto.PostsUpdateRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    //랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선
    @Transactional(readOnly = true)
    public List<PostsListResponseDTO> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDTO::new)     //.map(posts -> new PostsListResponseDto(posts))와 같음
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }

}
