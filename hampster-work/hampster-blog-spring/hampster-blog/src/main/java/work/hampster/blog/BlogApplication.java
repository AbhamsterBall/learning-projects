package work.hampster.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"work.hampster"})
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(Config.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  Hampster Blog is running!   ლ(´ڡ`ლ)ﾞ ");
	}

}
