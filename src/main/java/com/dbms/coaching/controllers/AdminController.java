package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    StudentDao studentDao;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin Page");
        model.addAttribute("message", "Welcome, Admin!");
        return "admin/admin";
    }

    @GetMapping("/admin/users")
    public String usersPortal(Model model) {
        model.addAttribute("title", "Users Portal");
        model.addAttribute("message", "View all the users");
        return "admin/usersPortal";
    }

    @GetMapping("/admin/students")
    public String studentsPortal(Model model) {
        model.addAttribute("title", "Students Portal");
        model.addAttribute("message", "View all the students");
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("students", students);
        return "admin/studentsPortal";
    }




}
