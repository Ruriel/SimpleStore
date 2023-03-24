package com.ruriel.simplestore;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableRabbit
public class SimpleStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleStoreApplication.class, args);
    }
}
