package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.FeedbackDao;
import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.dao.TeacherDao;
import com.dbms.coaching.models.Feedback;
import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.Teacher;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackDao feedbackDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @GetMapping("/admin/feedbacks")
    public String listFeedbacks(Model model) {
        model.addAttribute("title", "Academic Portal - Feedbacks");
        model.addAttribute("message", "View all the feedbacks");
        List<Feedback> feedbacks = feedbackDao.getAll();
        model.addAttribute("feedbacks", feedbacks);
        return "feedback/listFeedbacks";
    }

    @GetMapping("/admin/feedbacks/add")
    public String addFeedback(Model model) {
        model.addAttribute("title", "Academic Portal - Feedbacks");
        model.addAttribute("message", "Add Feedback");
        model.addAttribute("submessage1", "Add Feedback");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/feedbacks/add");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        List<Teacher> teachers = teacherDao.getAll();
        model.addAttribute("teachers", teachers);
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "feedback/addEditFeedback";
    }

    @PostMapping("/admin/feedbacks/add")
    public String addFeedback(@ModelAttribute("feedback") Feedback feedback, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Feedbacks");
            model.addAttribute("message", "Add Feedback");
            model.addAttribute("submessage1", "Add Feedback");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/feedbacks/add");
            List<Student> students = studentDao.getAll();
            model.addAttribute("students", students);
            List<Teacher> teachers = teacherDao.getAll();
            model.addAttribute("teachers", teachers);
            return "feedback/addEditFeedback";
        }
        feedbackDao.save(feedback);
        return "redirect:/admin/feedbacks";
    }

    @GetMapping("/admin/feedbacks/ST{studentId}/ET{employeeId}")
    public String viewFeedback(@PathVariable("studentId") int studentId, @PathVariable("employeeId") int employeeId,
            Model model) {
        model.addAttribute("title", "Academic Portal - Feedbacks");
        model.addAttribute("message", "View Feedback");
        model.addAttribute("submessage1", "Feedback Details");
        Feedback feedback = feedbackDao.get(studentId, employeeId);
        model.addAttribute("feedback", feedback);
        return "feedback/viewFeedback";
    }

    @GetMapping("/admin/feedbacks/ST{studentId}/ET{employeeId}/edit")
    public String editFeedback(@PathVariable("studentId") int studentId, @PathVariable("employeeId") int employeeId,
            Model model) {
        model.addAttribute("title", "Academic Portal - Feedbacks");
        model.addAttribute("message", "Edit Feedback");
        model.addAttribute("submessage1", "Edit Feedback");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/feedbacks/ST" + studentId + "/ET" + employeeId + "/edit");
        model.addAttribute("edit", "true");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        List<Teacher> teachers = teacherDao.getAll();
        model.addAttribute("teachers", teachers);
        Feedback feedback = feedbackDao.get(studentId, employeeId);
        model.addAttribute("feedback", feedback);
        return "feedback/addEditFeedback";
    }

    @PostMapping("/admin/feedbacks/ST{studentId}/ET{employeeId}/edit")
    public String editFeedback(@PathVariable("studentId") int studentId, @PathVariable("employeeId") int employeeId,
            @ModelAttribute("feedback") Feedback feedback, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Feedbacks");
            model.addAttribute("message", "Edit Feedback");
            model.addAttribute("submessage1", "Edit Feedback");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/feedbacks/ST" + studentId + "/ET" + employeeId + "/edit");
            model.addAttribute("edit", "true");
            List<Student> students = studentDao.getAll();
            model.addAttribute("students", students);
            List<Teacher> teachers = teacherDao.getAll();
            model.addAttribute("teachers", teachers);
            return "feedback/addEditFeedback";
        }
        feedbackDao.update(feedback);
        return "redirect:/admin/feedbacks";
    }

    @PostMapping("/admin/feedbacks/ST{studentId}/ET{employeeId}/respond")
    public ResponseEntity<Integer> respondFeedback(@PathVariable("studentId") int studentId,
            @PathVariable("employeeId") int employeeId,
            @RequestParam String response, Model model) {
        feedbackDao.respond(studentId, employeeId, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/feedbacks/ST{studentId}/ET{employeeId}/delete")
    public ResponseEntity<Integer> deleteFeedback(@PathVariable("studentId") int studentId,
            @PathVariable("employeeId") int employeeId, Model model) {
        feedbackDao.delete(studentId, employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
