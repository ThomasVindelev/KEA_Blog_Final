package com.keablog.demo.Controllers;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Objects.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession httpSession, @ModelAttribute User user, Model model, Database database) throws SQLException {

        if (database.verifyUser(user)) {
            httpSession.setAttribute(user.getUsername(), user);
            return "admin";
        } else {
            model.addAttribute("invalid", true);
            return "login";
        }

    }

}
