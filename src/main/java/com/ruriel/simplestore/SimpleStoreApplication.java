package com.ruriel.simplestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SimpleStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleStoreApplication.class, args);
    }
}
