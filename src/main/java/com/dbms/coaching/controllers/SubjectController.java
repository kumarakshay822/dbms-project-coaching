package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.SubjectDao;
import com.dbms.coaching.models.Subject;

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
public class SubjectController {
    @Autowired
    private SubjectDao subjectDao;

    @GetMapping("/admin/academics/subjects")
    public String listSubjects(Model model) {
        model.addAttribute("title", "Academic Portal - Subjects");
        model.addAttribute("message", "View all the subjects");
        List<Subject> subjects = subjectDao.getAll();
        model.addAttribute("subjects", subjects);
        return "subject/listSubjects";
    }

    @GetMapping("/admin/academics/subjects/add")
    public String addSubject(Model model) {
        model.addAttribute("title", "Academic Portal - Subjects");
        model.addAttribute("message", "Add Subject");
        model.addAttribute("submessage1", "Add Subject");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/subjects/add");
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/addEditSubject";
    }

    @PostMapping("/admin/academics/subjects/add")
    public String addSubject(@ModelAttribute("subject") Subject subject, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Subjects");
            model.addAttribute("message", "Add Subject");
            model.addAttribute("submessage1", "Add Subject");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/subjects/add");
            return "subject/addEditSubject";
        }
        subjectDao.save(subject);
        return "redirect:/admin/academics/subjects";
    }

    @GetMapping("/admin/academics/subjects/{subjectId}/edit")
    public String editSubject(@PathVariable("subjectId") String subjectId, Model model) {
        model.addAttribute("title", "Academic Portal - Subjects");
        model.addAttribute("message", "Edit Subject");
        model.addAttribute("submessage1", "Edit Subject");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/edit");
        model.addAttribute("edit", "true");
        Subject subject = subjectDao.get(subjectId);
        model.addAttribute("subject", subject);
        return "subject/addEditSubject";
    }

    @PostMapping("/admin/academics/subjects/{subjectId}/edit")
    public String editSubject(@PathVariable("subjectId") String subjectId, @ModelAttribute("subject") Subject subject,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Subjects");
            model.addAttribute("message", "Edit Subject");
            model.addAttribute("submessage1", "Edit Subject");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/edit");
            model.addAttribute("edit", "true");
            return "subject/addEditSubject";
        }
        subjectDao.update(subject);
        return "redirect:/admin/academics/subjects";
    }

    @GetMapping("/admin/academics/subjects/{subjectId}/delete")
    public ResponseEntity<Integer> deleteSubject(@PathVariable("subjectId") String subjectId, Model model) {
        subjectDao.delete(subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
