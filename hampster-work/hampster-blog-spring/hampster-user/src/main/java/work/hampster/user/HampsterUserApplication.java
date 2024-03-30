package work.hampster.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HampsterUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(Config.class, args);
    }

}
