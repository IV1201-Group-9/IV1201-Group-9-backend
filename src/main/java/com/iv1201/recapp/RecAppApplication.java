package com.iv1201.recapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring boot application main.
 */
@SpringBootApplication
public class RecAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecAppApplication.class, args);
		System.out.println("Hello, Server started");
	}

	/**
	 * Bean for Cors configuration of SpringMVC
	 * @return New WebMvcConfigurer with a mapping for the correct Cross-origin resource sharing reference
	 */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
//                        .addMapping("/api/v1/auth/signup")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD");
            }
        };
    }

}
