package com.keablog.demo.Controllers;

import com.keablog.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

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

    @Autowired
    MessageService service;
    @GetMapping("/blog")
    public String getBlog(Model model) throws SQLException {
        model.addAttribute("messages", service.readNewMessages());
        return "blog";
    }

}
