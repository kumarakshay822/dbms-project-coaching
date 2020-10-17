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
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.save(user);

        // TODO: Redirecting to login page
        securityService.autoLogin(user.getUsername(), user.getPassword());
        return "redirect:/welcome";
    }
    
}