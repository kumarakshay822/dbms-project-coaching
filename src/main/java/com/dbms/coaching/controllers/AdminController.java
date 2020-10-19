package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("submessage1", "Student Details");
        Student student = studentDao.get(studentId);
        model.addAttribute("student", student);
        System.out.println(student);
        return "admin/viewStudent";
    }

    @GetMapping("/admin/students/ST{studentId}/edit-student")
    public String editStudent(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Edit Student's profile");
        model.addAttribute("submessage1", "Edit Student Details");
        model.addAttribute("submessage2", "Step 1: Student Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        Student student = studentDao.get(studentId);
        model.addAttribute("student", student);
        System.out.println(student);
        return "admin/editStudent";
    }

    @PostMapping("/admin/students/ST{studentId}/edit-student")
    public String editStudent(@PathVariable("studentId") int studentId, @ModelAttribute("student") Student student,
            BindingResult bindingResult) {
        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            return "editStudent";
        }
        Student oldStudent = studentDao.get(studentId);

        User user = student.getUser();
        user.setUserId(oldStudent.getUser().getUserId());
        userDao.update(student.getUser());

        studentDao.update(student);
        return "redirect:/admin/students/ST{studentId}/edit-student-phone";
    }

    @GetMapping("/admin/students/ST{studentId}/edit-student-phone")
    public String editStudentPhone(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Edit Student's profile");
        model.addAttribute("submessage1", "Edit Student Details");
        model.addAttribute("submessage2", "Step 2: Student Phone Number");
        model.addAttribute("buttonmessage", "Proceed to Step 3");
        Student student = studentDao.get(studentId);
        int userId = student.getUser().getUserId();

        return "admin/editStudentPhone";
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

    @PostMapping("/admin/users/{userId}/edit/phoneNumber")
    public ResponseEntity<Integer> editUserPhone(@PathVariable("userId") int userId, Model model) {
        userDao.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
