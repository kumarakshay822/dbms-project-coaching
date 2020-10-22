package com.dbms.coaching.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dbms.coaching.dao.CourseDao;
import com.dbms.coaching.dao.CourseSubjectDao;
import com.dbms.coaching.dao.SubjectDao;
import com.dbms.coaching.models.Course;
import com.dbms.coaching.models.CourseSubjectDetails;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private CourseSubjectDao courseSubjectDao;

    @GetMapping("/admin/academics/courses")
    public String listCourses(Model model) {
        model.addAttribute("title", "Academic Portal - Courses");
        model.addAttribute("message", "View all the courses");
        List<Map<String, Object>> courses = courseDao.getAllList();
        List<CourseSubjectDetails> courseSubjects = courseSubjectDao.getAll();
        for (Map<String, Object> course : courses) {
            List<Subject> subjects = new ArrayList<>();
            for (CourseSubjectDetails courseSubject : courseSubjects) {
                if (courseSubject.getCourseId().equals(course.get("courseId"))) {
                    subjects.add(courseSubject.getSubject());
                }
            }
            course.put("subjects", subjects);
        }
        model.addAttribute("courses", courses);
        return "course/listCourses";
    }

    @GetMapping("/admin/academics/courses/add")
    public String addCourse(Model model) {
        model.addAttribute("title", "Academic Portal - Courses");
        model.addAttribute("message", "Add Course");
        model.addAttribute("submessage1", "Add Course");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/courses/add");
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/addEditCourse";
    }

    @PostMapping("/admin/academics/courses/add")
    public String addCourse(@ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Courses");
            model.addAttribute("message", "Add Course");
            model.addAttribute("submessage1", "Add Course");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/courses/add");
            return "course/addEditCourse";
        }
        courseDao.save(course);
        return "redirect:/admin/academics/courses";
    }

    @GetMapping("/admin/academics/courses/{courseId}/edit")
    public String editCourse(@PathVariable("courseId") String courseId, Model model) {
        model.addAttribute("title", "Academic Portal - Courses");
        model.addAttribute("message", "Edit Course");
        model.addAttribute("submessage1", "Edit Course");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/edit");
        model.addAttribute("edit", "true");
        Course course = courseDao.get(courseId);
        model.addAttribute("course", course);
        return "course/addEditCourse";
    }

    @PostMapping("/admin/academics/courses/{courseId}/edit")
    public String editCourse(@PathVariable("courseId") String courseId, @ModelAttribute("course") Course course,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Courses");
            model.addAttribute("message", "Edit Course");
            model.addAttribute("submessage1", "Edit Course");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/edit");
            model.addAttribute("edit", "true");
            return "course/addEditCourse";
        }
        courseDao.update(course);
        return "redirect:/admin/academics/courses";
    }

    @GetMapping("/admin/academics/courses/{courseId}/delete")
    public ResponseEntity<Integer> deleteCourse(@PathVariable("courseId") String courseId, Model model) {
        courseDao.delete(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/academics/courses/{courseId}/add-subject")
    public String addCourseSubject(@PathVariable("courseId") String courseId, Model model) {
        model.addAttribute("title", "Academic Portal - Courses");
        model.addAttribute("message", "Add Subject");
        model.addAttribute("submessage1", "Add Subject - " + courseId);
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/add-subject");
        List<Subject> subjectsPresent = subjectDao.getSubjectsInCourse(courseId);
        List<Subject> subjectsNotPresent = subjectDao.getSubjectsNotInCourse(courseId);
        model.addAttribute("subjectsPresent", subjectsPresent);
        model.addAttribute("subjectsNotPresent", subjectsNotPresent);
        return "course/addCourseSubject";
    }

    @PostMapping("/admin/academics/courses/{courseId}/add-subject")
    public ResponseEntity<String> addCourseSubject(@PathVariable("courseId") String courseId,
            @RequestParam String subjectId, Model model) {
        courseSubjectDao.save(courseId, subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/academics/courses/{courseId}/delete-subject")
    public ResponseEntity<Integer> deleteCourseSubject(@PathVariable("courseId") String courseId,
            @RequestParam String subjectId, Model model) {
        courseSubjectDao.delete(courseId, subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
