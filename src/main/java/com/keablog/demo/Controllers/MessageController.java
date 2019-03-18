package com.keablog.demo.Controllers;

import com.keablog.demo.Entities.Message;
import com.keablog.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public String postMessage(@ModelAttribute (name="Message") Message message, Model model) throws SQLException {
        model.addAttribute("title", message.getTitle());
        model.addAttribute("text", message.getText());
        messageService.newPost(message);
        return "redirect:/blog";
    }

    @PostMapping("/deletemessage")
    public String deleteMessage(@ModelAttribute (name="Message") Message message) throws SQLException {
        messageService.deleteMessage(message);
        return "redirect:/blog";
    }

    @GetMapping("/editmessage")
    public String editMessage() {
        return "edit";
    }

    @PostMapping("/editmessage")
    public String editMessage(@ModelAttribute (name="Message") Message message, Model model) {
        return "redirect:/blog";
    }

}
