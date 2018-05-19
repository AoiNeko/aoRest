package com.aoineko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
@SpringBootApplication
@RestController
public class App {

    @RequestMapping("/helloWorld")
    String home() {
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}