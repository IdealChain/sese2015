package sese2015.g3.goldenlion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("sese2015.g3.goldenlion.*")
public class GoldenlionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldenlionApplication.class, args);
    }
}
