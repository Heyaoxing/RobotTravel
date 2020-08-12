package com.bugzai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author
 */
@PropertySource("classpath:db.properties")
@SpringBootApplication
public class RobottravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobottravelApplication.class, args);
    }

}
