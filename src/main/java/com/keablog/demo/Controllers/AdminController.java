package com.keablog.demo.Controllers;

import com.keablog.demo.Entities.Chat;
import com.keablog.demo.Entities.Message;
import com.keablog.demo.Entities.User;
import com.keablog.demo.Service.MessageService;
import com.keablog.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/chat")
    public String getChat(Model model) throws SQLException {
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("chats", messageService.getChat());
        return "chat";
    }

    @PostMapping("/chat")
    public String sendChat(@ModelAttribute Chat chat) throws SQLException {
        messageService.newChat(chat);
        return "redirect:/blog";
    }

    @GetMapping("/admin/control")
    public String getControl(Model model) throws SQLException {
        model.addAttribute("users", userService.getUserList());
        return "control";
    }

    @GetMapping("/admin/adduser")
    public String addUser() {
        return "adduser";
    }

    @PostMapping("/admin/adduser")
    public String addUser(@ModelAttribute (name = "User") User user) throws SQLException {
        userService.addUser(user);
        return "redirect:/admin/control";
    }

    @PostMapping("/admin/deleteuser")
    public String deleteUser(@ModelAttribute (name="id") User user) throws SQLException {
        userService.deleteUser(user);
        return "redirect:/admin/control";
    }

    @PostMapping("/edituser")
    public String editUser(@ModelAttribute User user, Model model) {
        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("age", user.getAge());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("id_role", user.getId_role());
        return "edituser";
    }

    @PostMapping("/admin/edituser")
    public String editUser(@ModelAttribute User user) throws SQLException {
        userService.editUser(user);
        return "redirect:/admin/control";
    }

}