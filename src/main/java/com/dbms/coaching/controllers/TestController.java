package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.TestDao;
import com.dbms.coaching.dao.CourseDao;
import com.dbms.coaching.models.Test;
import com.dbms.coaching.models.Course;

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
public class TestController {
    @Autowired
    private TestDao testDao;

    @Autowired
    private CourseDao courseDao;

    @GetMapping("/admin/academics/tests")
    public String listTests(Model model) {
        model.addAttribute("title", "Academic Portal - Tests");
        model.addAttribute("message", "View all the tests");
        List<Test> tests = testDao.getAll();
        model.addAttribute("tests", tests);
        return "test/listTests";
    }

    @GetMapping("/admin/academics/tests/add")
    public String addTest1(Model model) {
        model.addAttribute("title", "Academic Portal - Tests");
        model.addAttribute("message", "Add Test");
        model.addAttribute("submessage1", "Add Test");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/tests/add");
        List<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        Test test = new Test();
        model.addAttribute("test", test);
        return "test/addEditTest";
    }

    @GetMapping("/admin/academics/courses/{courseId}/add-test")
    public String addTest2(@PathVariable(value = "courseId") String courseId, Model model) {
        model.addAttribute("title", "Academic Portal - Tests");
        model.addAttribute("message", "Add Test");
        model.addAttribute("submessage1", "Add Test");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/add-test");
        Course course = courseDao.get(courseId);
        model.addAttribute("course", course);
        Test test = new Test();
        model.addAttribute("test", test);
        return "test/addEditTest";
    }

    @PostMapping("/admin/academics/tests/add")
    public String addTest1(@ModelAttribute("test") Test test, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Tests");
            model.addAttribute("message", "Add Test");
            model.addAttribute("submessage1", "Add Test");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/tests/add");
            List<Course> courses = courseDao.getAll();
            model.addAttribute("courses", courses);
            return "test/addEditTest";
        }
        testDao.save(test);
        return "redirect:/admin/academics/tests";
    }

    @PostMapping("/admin/academics/courses/{courseId}/add-test")
    public String addTest2(@PathVariable(value = "courseId") String courseId, @ModelAttribute("test") Test test,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Tests");
            model.addAttribute("message", "Add Test");
            model.addAttribute("submessage1", "Add Test");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/courses/" + courseId);
            Course course = courseDao.get(courseId);
            model.addAttribute("course", course);
            return "test/addEditTest";
        }
        Course course = test.getCourse();
        course.setCourseId(courseId);
        test.setCourse(course);
        testDao.save(test);
        return "redirect:/admin/academics/courses/" + courseId;
    }

    @GetMapping("/admin/academics/tests/{testId}")
    public String viewTest(@PathVariable("testId") int testId, Model model) {
        model.addAttribute("title", "Academic Portal - Tests");
        model.addAttribute("message", "View Test");
        model.addAttribute("submessage1", "Test Details");
        Test test = testDao.get(testId);
        model.addAttribute("test", test);
        return "test/viewTest";
    }

    @GetMapping("/admin/academics/tests/{testId}/edit")
    public String editTest(@PathVariable("testId") int testId, Model model) {
        model.addAttribute("title", "Academic Portal - Tests");
        model.addAttribute("message", "Edit Test");
        model.addAttribute("submessage1", "Edit Test");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/tests/" + testId + "/edit");
        model.addAttribute("edit", "true");
        List<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        Test test = testDao.get(testId);
        model.addAttribute("test", test);
        return "test/addEditTest";
    }

    @PostMapping("/admin/academics/tests/{testId}/edit")
    public String editTest(@PathVariable("testId") int testId,
    @ModelAttribute("test") Test test, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Tests");
            model.addAttribute("message", "Edit Test");
            model.addAttribute("submessage1", "Edit Test");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/tests/" + testId + "/edit");
            model.addAttribute("edit", "true");
            List<Course> courses = courseDao.getAll();
            model.addAttribute("courses", courses);
            return "test/addEditTest";
        }
        testDao.update(test);
        return "redirect:/admin/academics/tests/" + testId;
    }

    @GetMapping("/admin/academics/tests/{testId}/delete")
    public ResponseEntity<Integer> deleteTest(@PathVariable("testId") int testId, Model model) {
        testDao.delete(testId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
