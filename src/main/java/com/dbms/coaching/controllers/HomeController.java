package com.dbms.coaching.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "home/index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin Page");
        model.addAttribute("message", "Welcome, Admin!");
        return "home/admin";
    }

    @GetMapping("/admin/academics")
    public String academicPortal(Model model) {
        model.addAttribute("title", "Academic Portal");
        model.addAttribute("message", "Manage all academic stuff");
        return "home/academic";
    }

}
