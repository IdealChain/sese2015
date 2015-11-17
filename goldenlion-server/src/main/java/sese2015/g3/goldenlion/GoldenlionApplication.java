package sese2015.g3.goldenlion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import sese2015.g3.goldenlion.security.config.SaltedSHA256PasswordEncoder;
import sese2015.g3.goldenlion.security.config.SecurityConfig;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class GoldenlionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldenlionApplication.class, args);
    }

    @Bean
    public SaltedSHA256PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        return new SaltedSHA256PasswordEncoder("salty");
    }
}
