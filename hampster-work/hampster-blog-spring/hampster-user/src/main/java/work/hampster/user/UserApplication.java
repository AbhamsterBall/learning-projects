package work.hampster.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(Config.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  Hampster User is running!   ლ(´ڡ`ლ)ﾞ ");
	}

}
