package com.keablog.demo.Controllers;

import com.keablog.demo.Objects.User;
import com.keablog.demo.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession httpSession, @ModelAttribute User user, Model model) throws SQLException {

        if (loginService.verifyUser(user)) {
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("username", user.getUsername());
            httpSession.setAttribute("id_role", user.getId_role());
            return "admin";
        } else {
            model.addAttribute("invalid", true);
            return "login";
        }
    }

}