package edu.hcmute.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class HomeController {
    @GetMapping("/admin/home")
    public String getProduct() {
        return "Home";
    }
    @GetMapping("/user/home")
    public String test() {
        return "Test url user";
    }
    @GetMapping("/auth/home")
    public String test2() {
        return "url khac";
    }
}
