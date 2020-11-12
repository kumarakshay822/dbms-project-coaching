package com.dbms.coaching.controllers;

import javax.validation.Valid;

import com.dbms.coaching.models.User;
import com.dbms.coaching.services.SecurityService;
import com.dbms.coaching.services.UserService;
import com.dbms.coaching.validators.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Transactional
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/user/register")
    public String register(Model model) {
        if (securityService.findLoggedInUserId() != 0) {
            return "redirect:/";
        }
        model.addAttribute("title", "Register Page");
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/user/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (securityService.findLoggedInUserId() != 0) {
            return "redirect:/";
        }
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Register Page");
            return "user/register";
        }
        userService.save(user);
        userService.sendVerificationEmail(user);
        return "redirect:/user/login?verificationEmailSent";
    }

}
