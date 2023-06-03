package com.jxx.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        log.info("request home");
        return "home";
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("request hello");
        return "hello-world";
    }
}
