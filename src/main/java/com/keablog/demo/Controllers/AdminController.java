package com.keablog.demo.Controllers;

import com.keablog.demo.Entities.Message;
import com.keablog.demo.Entities.User;
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

    @GetMapping("/admin/control")
    public String getControl(Model model) throws SQLException {
        model.addAttribute("users", userService.getUserList());
        return "control";
    }

    @PostMapping("/admin/deleteuser")
    public String deleteUser(@ModelAttribute (name="id") User user) throws SQLException {
        userService.deleteUser(user);
        return "redirect:/admin/control";
    }

    @GetMapping("/admin/edituser")
    public String editUser(@ModelAttribute  User user, Model model) {
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