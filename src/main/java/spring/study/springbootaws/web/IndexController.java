package spring.study.springbootaws.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.study.springbootaws.config.auth.LoginUser;
import spring.study.springbootaws.config.auth.dto.SessionUser;
import spring.study.springbootaws.service.posts.PostsService;
import spring.study.springbootaws.web.dto.PostsResponseDTO;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {      // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있습니다.
                                            // 여기서는 postsService.findAllDesc( )로 가져온 결과를
                                            // posts로 index.mustache에 전달합니다
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null)
            model.addAttribute("userName", user.getName());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDTO dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
