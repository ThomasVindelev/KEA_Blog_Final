package com.keablog.demo.Controllers;

import com.keablog.demo.Objects.Message;
import com.keablog.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class BlogController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @Autowired
    MessageService service;

    @GetMapping("/history")
    public String getHistory(Model model) throws SQLException {
        model.addAttribute("messages", service.getAllMessages());
        return "history";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/blog")
    public String getBlog(Model model) throws SQLException {
        model.addAttribute("messages", service.getNewMessages());
        return "blog";
    }



}
