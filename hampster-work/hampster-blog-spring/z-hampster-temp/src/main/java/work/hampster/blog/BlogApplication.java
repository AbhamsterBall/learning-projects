package work.hampster.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(Config.class, args);
	}

}
