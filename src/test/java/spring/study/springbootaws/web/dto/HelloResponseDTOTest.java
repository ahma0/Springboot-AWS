package spring.study.springbootaws.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
public class HelloResponseDTOTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        //then
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
