package com.dbms.coaching.controllers;

import com.dbms.coaching.models.User;
import com.dbms.coaching.services.SecurityService;
import com.dbms.coaching.services.UserService;
import com.dbms.coaching.validators.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register Page");
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Register Page");
            return "user/register";
        }
        String originalPassword = user.getPassword();
        userService.save(user);

        securityService.autoLogin(user.getUsername(), originalPassword);
        return "redirect:/";
    }

}
