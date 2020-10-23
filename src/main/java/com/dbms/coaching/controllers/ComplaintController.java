package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.ComplaintDao;
import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.models.Complaint;
import com.dbms.coaching.models.Student;

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
public class ComplaintController {
    @Autowired
    private ComplaintDao complaintDao;

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/admin/complaints")
    public String listComplaints(Model model) {
        model.addAttribute("title", "Academic Portal - Complaints");
        model.addAttribute("message", "View all the complaints");
        List<Complaint> complaints = complaintDao.getAll();
        model.addAttribute("complaints", complaints);
        return "complaint/listComplaints";
    }

    @GetMapping("/admin/complaints/add")
    public String addComplaint(Model model) {
        model.addAttribute("title", "Academic Portal - Complaints");
        model.addAttribute("message", "Add Complaint");
        model.addAttribute("submessage1", "Add Complaint");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/complaints/add");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        Complaint complaint = new Complaint();
        model.addAttribute("complaint", complaint);
        return "complaint/addEditComplaint";
    }

    @PostMapping("/admin/complaints/add")
    public String addComplaint(@ModelAttribute("complaint") Complaint complaint, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Complaints");
            model.addAttribute("message", "Add Complaint");
            model.addAttribute("submessage1", "Add Complaint");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/complaints/add");
            List<Student> students = studentDao.getAll();
            model.addAttribute("students", students);
            return "complaint/addEditComplaint";
        }
        complaintDao.save(complaint);
        return "redirect:/admin/complaints";
    }

    @GetMapping("/admin/complaints/{complaintId}")
    public String viewComplaint(@PathVariable("complaintId") int complaintId, Model model) {
        model.addAttribute("title", "Academic Portal - Courses");
        model.addAttribute("message", "View Complaint");
        model.addAttribute("submessage1", "Complaint Details");
        Complaint complaint = complaintDao.get(complaintId);
        model.addAttribute("complaint", complaint);
        return "complaint/viewComplaint";
    }

    @GetMapping("/admin/complaints/{complaintId}/edit")
    public String editComplaint(@PathVariable("complaintId") int complaintId, Model model) {
        model.addAttribute("title", "Academic Portal - Complaints");
        model.addAttribute("message", "Edit Complaint");
        model.addAttribute("submessage1", "Edit Complaint");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/complaints/" + complaintId + "/edit");
        model.addAttribute("edit", "true");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        Complaint complaint = complaintDao.get(complaintId);
        model.addAttribute("complaint", complaint);
        return "complaint/addEditComplaint";
    }

    @PostMapping("/admin/complaints/{complaintId}/edit")
    public String editComplaint(@PathVariable("complaintId") int complaintId, @ModelAttribute("complaint") Complaint complaint,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Complaints");
            model.addAttribute("message", "Edit Complaint");
            model.addAttribute("submessage1", "Edit Complaint");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/complaints/" + complaintId + "/edit");
            model.addAttribute("edit", "true");
            List<Student> students = studentDao.getAll();
            model.addAttribute("students", students);
            return "complaint/addEditComplaint";
        }
        complaintDao.update(complaint);
        return "redirect:/admin/complaints";
    }

    @PostMapping("/admin/complaints/{complaintId}/resolve")
    public ResponseEntity<Integer> resolveComplaint(@PathVariable("complaintId") int complaintId,
            @RequestParam String response, Model model) {
        complaintDao.resolve(complaintId, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/complaints/{complaintId}/delete")
    public ResponseEntity<Integer> deleteComplaint(@PathVariable("complaintId") int complaintId, Model model) {
        complaintDao.delete(complaintId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
