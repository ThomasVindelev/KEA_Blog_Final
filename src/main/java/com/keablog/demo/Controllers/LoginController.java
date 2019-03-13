package com.keablog.demo.Controllers;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Objects.Message;
import com.keablog.demo.Objects.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class LoginController {

    Database database;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model, Database database) throws SQLException {

        if (database.verifyUser(user)) {
            return "admin";
        } else {
            model.addAttribute("invalid", true);
            return "login";
        }

    }

}
