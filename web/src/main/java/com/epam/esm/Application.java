package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The entry point of Spring Boot application.
 */
@EnableWebMvc
@SpringBootApplication(scanBasePackages = "com.epam.esm")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}