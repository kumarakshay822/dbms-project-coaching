package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.GuardianDao;
import com.dbms.coaching.dao.GuardianPhoneNumberDao;
import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.dao.UserPhoneNumberDao;
import com.dbms.coaching.models.Guardian;
import com.dbms.coaching.models.GuardianPhoneNumber;
import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.User;
import com.dbms.coaching.models.UserPhoneNumber;
import com.dbms.coaching.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class AdminController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GuardianDao guardianDao;

    @Autowired
    private UserPhoneNumberDao userPhoneNumberDao;

    @Autowired
    private GuardianPhoneNumberDao guardianPhoneNumberDao;

    @Autowired
    private UserService userService;

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

    @GetMapping("/admin/users")
    public String usersPortal(Model model) {
        model.addAttribute("title", "Users Portal");
        model.addAttribute("message", "View all the users");
        return "user/usersPortal";
    }

    @GetMapping("/admin/students")
    public String studentsPortal(Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "View all the students");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        return "student/studentsPortal";
    }

    @GetMapping("/admin/students/add")
    public String addStudent(Model model) {
        // TODO: Create student and guardian together
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Student Details");
        model.addAttribute("submessage2", "Step 1: Student Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/admin/students/add");
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/addEditStudent";
    }

    @PostMapping("/admin/students/add")
    public String addStudent(@ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Student Portal");
            model.addAttribute("message", "Create Student's profile");
            model.addAttribute("submessage1", "Add Student Details");
            model.addAttribute("submessage2", "Step 1: Student Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/admin/students/add");
            return "student/addEditStudent";
        }

        User user = student.getUser();
        user.setPassword("password");
        user = userService.save(user);

        student.setUser(user);
        student = studentDao.save(student);

        return "redirect:/admin/students/ST" + student.getStudentId() + "/add-student-phone";
    }

    @GetMapping("/admin/students/ST{studentId}")
    public String viewStudent(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "View Student's profile");
        model.addAttribute("submessage1", "Student Details");
        Student student = studentDao.get(studentId);
        int userId = student.getUser().getUserId();
        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
        Guardian guardian = guardianDao.getByStudentId(studentId);
        List<GuardianPhoneNumber> guardianPhoneNumbers = guardianPhoneNumberDao.getByStudentId(studentId);
        model.addAttribute("student", student);
        model.addAttribute("guardian", guardian);
        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
        model.addAttribute("guardianPhoneNumbers", guardianPhoneNumbers);
        return "student/viewStudent";
    }

    @GetMapping("/admin/students/ST{studentId}/edit-student")
    public String editStudent(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Edit Student's profile");
        model.addAttribute("submessage1", "Edit Student Details");
        model.addAttribute("submessage2", "Step 1: Student Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/admin/students/ST" + studentId + "/edit-student");
        model.addAttribute("edit", "true");
        Student student = studentDao.get(studentId);
        model.addAttribute("student", student);
        return "student/addEditStudent";
    }

    @PostMapping("/admin/students/ST{studentId}/edit-student")
    public String editStudent(@PathVariable("studentId") int studentId, @ModelAttribute("student") Student student,
    BindingResult bindingResult, Model model) {
        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Student Portal");
            model.addAttribute("message", "Edit Student's profile");
            model.addAttribute("submessage1", "Edit Student Details");
            model.addAttribute("submessage2", "Step 1: Student Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/admin/students/ST" + studentId + "/edit-student");
            model.addAttribute("edit", "true");
            return "student/addEditStudent";
        }
        Student oldStudent = studentDao.get(studentId);

        User user = student.getUser();
        user.setUserId(oldStudent.getUser().getUserId());

        student.setUser(user);

        userDao.update(user);
        studentDao.update(student);
        return "redirect:/admin/students/ST{studentId}/edit-student-phone";
    }

    @GetMapping("/admin/students/ST{studentId}/add-student-phone")
    public String addStudentPhone(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Student Details");
        model.addAttribute("submessage2", "Step 2: Student Phone Number");
        model.addAttribute("buttonmessage", "Proceed to Step 3");
        Student student = studentDao.get(studentId);
        int userId = student.getUser().getUserId();
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "student/addEditStudentPhone";
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
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "student/addEditStudentPhone";
    }

    // @RequestMapping("/username")
    // @ResponseBody
    // public void currentUserName() {
    //     Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    //     UserDetails userPrincipal = (UserDetails) auth.getPrincipal();
    //     System.out.println(userPrincipal.getAuthorities());
    //     System.out.println(userPrincipal.getUsername());
    //     System.out.println(userPrincipal.getPassword());

    // }

}
