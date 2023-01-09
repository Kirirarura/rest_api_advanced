package com.epam.esm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class that contains configurations for a web project
 */
@Configuration
@ComponentScan("com.epam.esm")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
}
