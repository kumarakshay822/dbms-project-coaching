package com.dbms.coaching.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dbms.coaching.dao.ResultDao;
import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.models.Result;
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

@Controller
public class ResultController {
    @Autowired
    private ResultDao resultDao;

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/admin/academics/tests/{testId}/results")
    public String listResults(@PathVariable("testId") int testId, Model model) {
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "View all the results");
        List<Result> results = resultDao.getAllByTestId(testId);
        model.addAttribute("results", results);
        Set<Integer> marks = new LinkedHashSet<>();
        for (Result result : results) {
            marks.add(result.getMarksScored());
        }
        ArrayList<Integer> marksList = new ArrayList<>();
        marksList.addAll(marks);
        Collections.sort(marksList, Collections.reverseOrder());
        Map<Integer, Integer> marksToRank = new HashMap<>();
        int count = 1;
        for (Integer mark : marksList) {
            marksToRank.put(mark, count++);
        }
        model.addAttribute("marksToRank", marksToRank);
        return "result/listResults";
    }

    @GetMapping("/admin/academics/tests/{testId}/results/add")
    public String addResult(@PathVariable("testId") int testId, Model model) {
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "Add Result");
        model.addAttribute("submessage1", "Add Result");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/tests/" + testId + "/results/add");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        Result result = new Result();
        model.addAttribute("result", result);
        return "result/addEditResult";
    }

    @PostMapping("/admin/academics/tests/{testId}/results/add")
    public String addResult(@PathVariable("testId") int testId, @ModelAttribute("result") Result result,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Results");
            model.addAttribute("message", "Add Result");
            model.addAttribute("submessage1", "Add Result");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/tests/" + testId + "/results/add");
            List<Student> students = studentDao.getAll();
            model.addAttribute("students", students);
            return "result/addEditResult";
        }
        System.out.println(result);
        resultDao.save(result);
        return "redirect:/admin/academics/tests/" + testId + "/results";
    }

    @GetMapping("/admin/academics/tests/{testId}/results/ST{studentId}")
    public String viewResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId,
    Model model) {
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "View Result");
        model.addAttribute("submessage1", "Result Details");
        Result result = resultDao.get(testId, studentId);
        model.addAttribute("result", result);
        return "result/viewResult";
    }

    @GetMapping("/admin/academics/tests/{testId}/results/ST{studentId}/edit")
    public String editResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId,
    Model model) {
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "Edit Result");
        model.addAttribute("submessage1", "Edit Result");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/tests/" + testId + "/results/ST" + studentId + "/edit");
        model.addAttribute("edit", "true");
        Result result = resultDao.get(testId, studentId);
        model.addAttribute("result", result);
        return "result/addEditResult";
    }

    @PostMapping("/admin/academics/tests/{testId}/results/ST{studentId}/edit")
    public String editResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId,
    @ModelAttribute("result") Result result, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Results");
            model.addAttribute("message", "Edit Result");
            model.addAttribute("submessage1", "Edit Result");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/tests/" + testId + "/results/ST" + studentId + "/edit");
            model.addAttribute("edit", "true");
            return "result/addEditResult";
        }
        Student student = result.getStudent();
        student.setStudentId(studentId);
        result.setStudent(student);

        resultDao.update(result);
        return "redirect:/admin/academics/tests/" + testId + "/results/ST" + studentId;
    }

    @GetMapping("/admin/academics/tests/{testId}/results/ST{studentId}/delete")
    public ResponseEntity<Integer> deleteResult(@PathVariable("testId") int testId,
    @PathVariable("studentId") int studentId, Model model) {
        resultDao.delete(testId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
