package spring.study.springbootaws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  //JpaAudition 활성화
public class JpaConfig {
}
