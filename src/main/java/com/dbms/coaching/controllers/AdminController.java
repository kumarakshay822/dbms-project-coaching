package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {
    @Autowired
    UserDao userDao;

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
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "View all the students");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        return "admin/studentsPortal";
    }

    @GetMapping("/admin/students/add")
    public String addStudent(Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Add a student");
        return "admin/addStudent";
    }

    @GetMapping("/admin/students/ST{studentId}")
    public String viewStudent(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "View Student's profile");
        return "admin/viewStudent";
    }

    @GetMapping("/admin/students/ST{studentId}/edit")
    public String editStudent(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Edit Student's profile");
        return "admin/editStudent";
    }

    @GetMapping("/admin/users/{userId}/activate")
    public ResponseEntity<Integer> activateUser(@PathVariable("userId") int userId, Model model) {
        userDao.activate(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/users/{userId}/delete")
    public ResponseEntity<Integer> deleteUser(@PathVariable("userId") int userId, Model model) {
        userDao.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
