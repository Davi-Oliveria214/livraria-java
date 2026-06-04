package com.livrariaJava;

import com.livrariaJava.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class LivrariaAplication {
    public static void main(String[] args) {
        SpringApplication.run(LivrariaAplication.class, args);
    }
}