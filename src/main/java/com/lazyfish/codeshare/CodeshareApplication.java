package com.lazyfish.codeshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CodeshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeshareApplication.class, args);
    }

}
