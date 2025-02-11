package work.hampster.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"work.hampster"})
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  Hampster User is running!   ლ(´ڡ`ლ)ﾞ ");
	}

}
