package spring.study.springbootaws.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import spring.study.springbootaws.config.auth.SecurityConfig;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//    @RunWith(SpringRunner.class)   JUnit5에서는 쓸 필요 없음
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = SecurityConfig.class
        )
})
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;        //웹 api 테스트할 때 사용

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))          // MockMVC를 통해 get 요청
                .andExpect(status().isOk())             // status 확인
                .andExpect(content().string(hello));    // string값 확인
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDTO가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }

}
