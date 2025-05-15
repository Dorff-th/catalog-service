package com.polarbookshop.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String getGreeting() {
        return "도서 카탈로스 서비스에 오신것을 존나 환영합니다.";
    }
}
