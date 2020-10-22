package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.BatchDao;
import com.dbms.coaching.dao.CourseDao;
import com.dbms.coaching.models.Batch;
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
public class BatchController {
    @Autowired
    private BatchDao batchDao;

    @Autowired
    private CourseDao courseDao;

    @GetMapping("/admin/academics/batches")
    public String listBatches(Model model) {
        model.addAttribute("title", "Academic Portal - Batches");
        model.addAttribute("message", "View all the batches");
        List<Batch> batches = batchDao.getAll();
        model.addAttribute("batches", batches);
        return "batch/listBatches";
    }

    @GetMapping("/admin/academics/batches/add")
    public String addBatch(Model model) {
        model.addAttribute("title", "Academic Portal - Batches");
        model.addAttribute("message", "Add Batch");
        model.addAttribute("submessage1", "Add Batch");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/batches/add");
        List<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        Batch batch = new Batch();
        model.addAttribute("batch", batch);
        return "batch/addEditBatch";
    }

    @PostMapping("/admin/academics/batches/add")
    public String addBatch(@ModelAttribute("batch") Batch batch, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Batches");
            model.addAttribute("message", "Add Batch");
            model.addAttribute("submessage1", "Add Batch");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/batches/add");
            List<Course> courses = courseDao.getAll();
            model.addAttribute("courses", courses);
            return "batch/addEditBatch";
        }
        batchDao.save(batch);
        return "redirect:/admin/academics/batches";
    }

    @GetMapping("/admin/academics/batches/{courseId}-{batchId}/edit")
    public String editBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId,
            Model model) {
        model.addAttribute("title", "Academic Portal - Batches");
        model.addAttribute("message", "Edit Batch");
        model.addAttribute("submessage1", "Edit Batch");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/batches/" + courseId + "-" + batchId + "/edit");
        model.addAttribute("edit", "true");
        List<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        Batch batch = batchDao.get(batchId, courseId);
        model.addAttribute("batch", batch);
        return "batch/addEditBatch";
    }

    @PostMapping("/admin/academics/batches/{courseId}-{batchId}/edit")
    public String editBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId,
            @ModelAttribute("batch") Batch batch, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Batches");
            model.addAttribute("message", "Edit Batch");
            model.addAttribute("submessage1", "Edit Batch");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/batches/" + courseId + "-" + batchId + "/edit");
            model.addAttribute("edit", "true");
            List<Course> courses = courseDao.getAll();
            model.addAttribute("courses", courses);
            return "batch/addEditBatch";
        }
        Course course = batch.getCourse();
        course.setCourseId(courseId);
        batch.setCourse(course);

        batchDao.update(batch);
        return "redirect:/admin/academics/batches";
    }

    @GetMapping("/admin/academics/batches/{courseId}-{batchId}/delete")
    public ResponseEntity<Integer> deleteBatch(@PathVariable("courseId") String courseId,
            @PathVariable("batchId") String batchId, Model model) {
        batchDao.delete(batchId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
