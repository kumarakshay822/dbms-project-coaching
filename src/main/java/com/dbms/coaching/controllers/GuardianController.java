package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.GuardianDao;
import com.dbms.coaching.dao.GuardianPhoneNumberDao;
import com.dbms.coaching.models.Guardian;
import com.dbms.coaching.models.GuardianPhoneNumber;

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
public class GuardianController {
    @Autowired
    private GuardianDao guardianDao;

    @Autowired
    private GuardianPhoneNumberDao guardianPhoneNumberDao;

    @GetMapping("/admin/students/ST{studentId}/edit-guardian")
    public String editGuardian(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Edit Student's profile");
        model.addAttribute("submessage1", "Edit Student Details");
        model.addAttribute("submessage2", "Step 3: Guardian Details");
        model.addAttribute("buttonmessage", "Proceed to Step 4");
        model.addAttribute("submiturl", "/admin/students/ST" + studentId + "/edit-guardian");
        model.addAttribute("edit", "true");
        Guardian guardian = guardianDao.getByStudentId(studentId);
        if (guardian == null) {
            return "redirect:/admin/students/ST{studentId}/add-guardian";
        }
        model.addAttribute("guardian", guardian);
        return "guardian/addEditGuardian";
    }

    @PostMapping("/admin/students/ST{studentId}/edit-guardian")
    public String editGuardian(@PathVariable("studentId") int studentId, @ModelAttribute("guardian") Guardian guardian,
    BindingResult bindingResult, Model model) {
        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Student Portal");
            model.addAttribute("message", "Edit Student's profile");
            model.addAttribute("submessage1", "Edit Student Details");
            model.addAttribute("submessage2", "Step 3: Guardian Details");
            model.addAttribute("buttonmessage", "Proceed to Step 4");
            model.addAttribute("submiturl", "/admin/students/ST" + studentId + "/edit-guardian");
            model.addAttribute("edit", "true");
            return "guardian/addEditGuardian";
        }
        guardianDao.update(guardian);
        return "redirect:/admin/students/ST{studentId}/edit-guardian-phone";
    }

    @GetMapping("/admin/students/ST{studentId}/add-guardian")
    public String addGuardian(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Student Details");
        model.addAttribute("submessage2", "Step 3: Guardian Details");
        model.addAttribute("buttonmessage", "Proceed to Step 4");
        model.addAttribute("submiturl", "/admin/students/ST" + studentId +"/add-guardian");
        Guardian guardian = new Guardian();
        model.addAttribute("guardian", guardian);
        guardian.setStudentId(studentId);
        return "guardian/addEditGuardian";
    }

    @PostMapping("/admin/students/ST{studentId}/add-guardian")
    public String addGuardian(@PathVariable("studentId") int studentId, @ModelAttribute("guardian") Guardian guardian,
    BindingResult bindingResult, Model model) {
        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Student Portal");
            model.addAttribute("message", "Create Student's profile");
            model.addAttribute("submessage1", "Add Student Details");
            model.addAttribute("submessage2", "Step 3: Guardian Details");
            model.addAttribute("buttonmessage", "Proceed to Step 4");
            model.addAttribute("submiturl", "/admin/students/ST" + studentId +"/add-guardian");
            return "guardian/addEditGuardian";
        }
        guardianDao.save(guardian);
        return "redirect:/admin/students/ST{studentId}/add-guardian-phone";
    }

    @GetMapping("/admin/students/ST{studentId}/edit-guardian-phone")
    public String editGuardianPhone(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Edit Student's profile");
        model.addAttribute("submessage1", "Edit Student Details");
        model.addAttribute("submessage2", "Step 4: Guardian Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        List<GuardianPhoneNumber> phoneNumbers = guardianPhoneNumberDao.getByStudentId(studentId);
        Guardian guardian = guardianDao.getByStudentId(studentId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("guardian", guardian);
        return "guardian/addEditGuardianPhone";
    }

    @GetMapping("/admin/students/ST{studentId}/add-guardian-phone")
    public String addGuardianPhone(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Student Details");
        model.addAttribute("submessage2", "Step 4: Guardian Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        List<GuardianPhoneNumber> phoneNumbers = guardianPhoneNumberDao.getByStudentId(studentId);
        Guardian guardian = guardianDao.getByStudentId(studentId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("guardian", guardian);
        return "guardian/addEditGuardianPhone";
    }

    @PostMapping("/admin/students/ST{studentId}/add-guardian-phone")
    public ResponseEntity<Integer> addGuardianPhoneNumber(@PathVariable("studentId") int studentId,
            @RequestParam String phoneNumber, @RequestParam String name, Model model) {
        GuardianPhoneNumber guardianPhoneNumber = new GuardianPhoneNumber(phoneNumber, name, studentId);
        guardianPhoneNumberDao.save(guardianPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/students/ST{studentId}/delete-guardian-phone")
    public ResponseEntity<Integer> deleteGuardianPhoneNumber(@PathVariable("studentId") int studentId,
            @RequestParam String phoneNumber, @RequestParam String name, Model model) {
        GuardianPhoneNumber guardianPhoneNumber = new GuardianPhoneNumber(phoneNumber, name, studentId);
        guardianPhoneNumberDao.delete(guardianPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
