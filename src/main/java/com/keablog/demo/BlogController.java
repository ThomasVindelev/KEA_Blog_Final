package com.keablog.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/blog")
    public String getBlog() {
        return "blog";
    }

}