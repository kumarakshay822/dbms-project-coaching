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
import com.dbms.coaching.services.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {
    @Autowired
    private ResultDao resultDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SecurityService securityService;

    public Map<Integer, Integer> getMarksToRank(List<Result> results) {
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
        return marksToRank;
    }

    public boolean checkStudentAppearedInTest(int testId) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (role.equals("student")) {
            int studentId = studentDao.getStudentIdByUserId(userId);
            int isAppearedInTest = resultDao.isStudentAppearedInTest(testId, studentId);
            if (isAppearedInTest == 0)
                throw new AccessDeniedException("You have not appeared in this test");
        }
        return true;
    }

    @GetMapping({ "/admin/academics/tests/{testId}/results", "/student/academics/tests/{testId}/results",
            "/teacher/academics/tests/{testId}/results", "/staff/academics/tests/{testId}/results" })
    public String listResults(@PathVariable("testId") int testId, Model model) {
        checkStudentAppearedInTest(testId);
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "View all the results");
        List<Result> results = resultDao.getAllByTestId(testId);
        model.addAttribute("results", results);
        Map<Integer, Integer> marksToRank = getMarksToRank(results);
        model.addAttribute("marksToRank", marksToRank);
        return "result/listResults";
    }

    @GetMapping({ "/admin/academics/tests/{testId}/results/add", "/staff/academics/tests/{testId}/results/add" })
    public String addResult(@PathVariable("testId") int testId, Model model) {
        // TODO: Display total marks here and in list view, also validate
        String role = securityService.findLoggedInUserRole();
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "Add Result");
        model.addAttribute("submessage1", "Add Result");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/add");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        Result result = new Result();
        model.addAttribute("result", result);
        return "result/addEditResult";
    }

    @PostMapping({ "/admin/academics/tests/{testId}/results/add", "/staff/academics/tests/{testId}/results/add" })
    public String addResult(@PathVariable("testId") int testId, @ModelAttribute("result") Result result,
            BindingResult bindingResult, Model model) {
        String role = securityService.findLoggedInUserRole();
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Results");
            model.addAttribute("message", "Add Result");
            model.addAttribute("submessage1", "Add Result");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/add");
            List<Student> students = studentDao.getAll();
            model.addAttribute("students", students);
            return "result/addEditResult";
        }
        resultDao.save(result);
        return "redirect:/" + role + "/academics/tests/" + testId + "/results";
    }

    @GetMapping({ "/admin/academics/tests/{testId}/results/ST{studentId}", "/staff/academics/tests/{testId}/results/ST{studentId}" })
    public String viewResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId,
    Model model) {
        // TODO: Change true / false in jsp to Yes / No
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "View Result");
        model.addAttribute("submessage1", "Result Details");
        Result result = resultDao.get(testId, studentId);
        model.addAttribute("result", result);
        return "result/viewResult";
    }

    @GetMapping({ "/admin/academics/tests/{testId}/results/ST{studentId}/edit", "/staff/academics/tests/{testId}/results/ST{studentId}/edit" })
    public String editResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId,
    Model model) {
        String role = securityService.findLoggedInUserRole();
        model.addAttribute("title", "Academic Portal - Results");
        model.addAttribute("message", "Edit Result");
        model.addAttribute("submessage1", "Edit Result");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/ST" + studentId + "/edit");
        model.addAttribute("edit", "true");
        Result result = resultDao.get(testId, studentId);
        model.addAttribute("result", result);
        return "result/addEditResult";
    }

    @PostMapping({ "/admin/academics/tests/{testId}/results/ST{studentId}/edit", "/staff/academics/tests/{testId}/results/ST{studentId}/edit" })
    public String editResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId,
    @ModelAttribute("result") Result result, BindingResult bindingResult, Model model) {
        String role = securityService.findLoggedInUserRole();
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Results");
            model.addAttribute("message", "Edit Result");
            model.addAttribute("submessage1", "Edit Result");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/ST" + studentId + "/edit");
            model.addAttribute("edit", "true");
            return "result/addEditResult";
        }
        Student student = result.getStudent();
        student.setStudentId(studentId);
        result.setStudent(student);

        if (role.equals("staff")) {
            resultDao.updateMarks(result);
        }
        else {
            resultDao.update(result);
        }
        return "redirect:/" + role + "/academics/tests/" + testId + "/results/ST" + studentId;
    }

    @GetMapping({ "/admin/academics/tests/{testId}/results/ST{studentId}/delete", "/staff/academics/tests/{testId}/results/ST{studentId}/delete" })
    public ResponseEntity<Integer> deleteResult(@PathVariable("testId") int testId,
    @PathVariable("studentId") int studentId, Model model) {
        resultDao.delete(testId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping({ "/admin/academics/tests/{testId}/results-recheck", "/staff/academics/tests/{testId}/results-recheck" })
    public String listRechecks(@PathVariable("testId") int testId, Model model) {
        model.addAttribute("title", "Academic Portal - Results Recheck");
        model.addAttribute("message", "View all the recheck applied by students");
        List<Result> results = resultDao.getAllRechecksByTestId(testId);
        model.addAttribute("results", results);
        return "result/listRechecks";
    }

    @GetMapping("/student/academics/tests/{testId}/results-recheck")
    public String studentRechecks(@PathVariable("testId") int testId, Model model) {
        checkStudentAppearedInTest(testId);
        model.addAttribute("title", "Academic Portal - Results Recheck");
        model.addAttribute("message", "View Recheck Status or Apply for Recheck");
        model.addAttribute("submessage1", "Recheck Details");
        model.addAttribute("submiturl", "/student/academics/tests/" + testId + "/results-recheck");
        int userId = securityService.findLoggedInUserId();
        int studentId = studentDao.getStudentIdByUserId(userId);
        Result result = resultDao.get(testId, studentId);
        model.addAttribute("result", result);
        return "result/viewStudentRecheck";
    }

    @PostMapping("/student/academics/tests/{testId}/results-recheck")
    public String studentRechecks(@PathVariable("testId") int testId, @ModelAttribute("result") Result result,
            BindingResult bindingResult, Model model) {
        checkStudentAppearedInTest(testId);
        int userId = securityService.findLoggedInUserId();
        int studentId = studentDao.getStudentIdByUserId(userId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Results Recheck");
            model.addAttribute("message", "View Recheck Status or Apply for Recheck");
            model.addAttribute("submessage1", "Recheck Details");
            model.addAttribute("submiturl", "/student/academics/tests/" + testId + "/results-recheck");
            return "result/viewStudentRecheck";
        }
        resultDao.applyForRecheck(testId, studentId, result.getRecheckComments());
        return "redirect:/student/academics/tests/" + testId + "/results-recheck";
    }

    @PostMapping({ "/admin/academics/tests/{testId}/results-recheck/{studentId}",
            "/staff/academics/tests/{testId}/results-recheck/{studentId}" })
    public ResponseEntity<String> addUserPhoneNumber(@PathVariable("testId") int testId,
            @PathVariable("studentId") int studentId, @RequestParam int newMarks, Model model) {
        resultDao.updateMarksAndMarkDone(testId, studentId, newMarks);
        // TODO: Validate above
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
