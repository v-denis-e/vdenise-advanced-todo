package kz.vdenise.vdeniseadvancedtodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VdeniseAdvancedTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VdeniseAdvancedTodoApplication.class, args);
	}

}
