package com.dbms.coaching.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "The username and password combination is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        model.addAttribute("title", "Login Page");
        return "user/login";
    }

}
