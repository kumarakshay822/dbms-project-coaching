package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.BatchDao;
import com.dbms.coaching.dao.CourseDao;
import com.dbms.coaching.dao.EnrollmentDao;
import com.dbms.coaching.dao.StudentDao;
import com.dbms.coaching.dao.TransactionDao;
import com.dbms.coaching.models.Course;
import com.dbms.coaching.models.Enrollment;
import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.Transaction;

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
public class EnrollmentController {
    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private BatchDao batchDao;

    @Autowired
    private CourseDao courseDao;

    @GetMapping("/admin/academics/enrollments")
    public String listEnrollments(Model model) {
        model.addAttribute("title", "Academic Portal - Enrollments");
        model.addAttribute("message", "View all the enrollments");
        List<Enrollment> enrollments = enrollmentDao.getAll();
        model.addAttribute("enrollments", enrollments);
        List<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        model.addAttribute("normal", true);
        return "enrollment/listEnrollments";
    }

    @GetMapping("/admin/academics/courses/{courseId}/enrollments")
    public String listEnrollmentsByCourse(@PathVariable("courseId") String courseId, Model model) {
        model.addAttribute("title", "Academic Portal - Enrollments");
        model.addAttribute("message", "View enrollments by Course");
        model.addAttribute("submessage1", "Course " + courseId);
        List<Enrollment> enrollments = enrollmentDao.getAllByCourseId(courseId);
        model.addAttribute("enrollments", enrollments);
        return "enrollment/listEnrollments";
    }

    @GetMapping("/admin/academics/courses/{courseId}/{batchId}/enrollments")
    public String listEnrollmentsByBatch(@PathVariable("courseId") String courseId,
    @PathVariable("batchId") String batchId, Model model) {
        model.addAttribute("title", "Academic Portal - Enrollments");
        model.addAttribute("message", "View enrollments by Batch");
        model.addAttribute("submessage1", "Batch " + batchId + " of Course " + courseId);
        List<Enrollment> enrollments = enrollmentDao.getAllByBatch(courseId, batchId);
        model.addAttribute("enrollments", enrollments);
        return "enrollment/listEnrollments";
    }

    @GetMapping("/admin/academics/courses/{courseId}/{batchId}/enrollments/add")
    public String addEnrollment(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, Model model) {
        model.addAttribute("title", "Academic Portal - Enrollments");
        model.addAttribute("message", "Add Enrollment");
        model.addAttribute("submessage1", "Add Enrollment");
        model.addAttribute("buttonmessage", "Pay and Finish");
        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/" + batchId + "/enrollments/add");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        Transaction transaction = new Transaction();
        int amount = batchDao.getFee(batchId, courseId);
        transaction.setAmount(amount);
        transaction.setTransactionMode("Offline");
        Enrollment enrollment = new Enrollment();
        enrollment.setTransaction(transaction);
        enrollment.setCourseId(courseId);
        enrollment.setBatchId(batchId);
        model.addAttribute("enrollment", enrollment);
        return "enrollment/addEditEnrollment";
    }

    @PostMapping("/admin/academics/courses/{courseId}/{batchId}/enrollments/add")
    public String addEnrollment(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId,
            @ModelAttribute("enrollment") Enrollment enrollment, BindingResult bindingResult, Model model) {
        // TODO: Don't allow enrolling in more than one batches of a course
        Transaction transaction = new Transaction();
        int amount = batchDao.getFee(batchId, courseId);
        transaction.setAmount(amount);
        transaction.setTransactionMode("Offline");
        enrollment.setTransaction(transaction);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Enrollments");
            model.addAttribute("message", "Add Enrollment");
            model.addAttribute("submessage1", "Add Enrollment");
            model.addAttribute("buttonmessage", "Pay and Finish");
            model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/" + batchId + "/enrollments/add");
            List<Student> students = studentDao.getAll();
            model.addAttribute("students", students);
            return "enrollment/addEditEnrollment";
        }
        transaction = transactionDao.save(enrollment.getTransaction());
        enrollment.setTransaction(transaction);
        enrollmentDao.save(enrollment);
        return "redirect:/admin/academics/enrollments";
    }

    @GetMapping("/admin/academics/enrollments/{enrollmentId}")
    public String viewEnrollment(@PathVariable("enrollmentId") int enrollmentId, Model model) {
        model.addAttribute("title", "Academic Portal - Courses");
        model.addAttribute("message", "View Enrollment");
        model.addAttribute("submessage1", "Enrollment Details");
        Enrollment enrollment = enrollmentDao.get(enrollmentId);
        model.addAttribute("enrollment", enrollment);
        return "enrollment/viewEnrollment";
    }

    @GetMapping("/admin/academics/enrollments/{enrollmentId}/edit")
    public String editEnrollment(@PathVariable("enrollmentId") int enrollmentId, Model model) {
        model.addAttribute("title", "Academic Portal - Enrollments");
        model.addAttribute("message", "Edit Enrollment");
        model.addAttribute("submessage1", "Edit Enrollment");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/enrollments/" + enrollmentId + "/edit");
        model.addAttribute("edit", "true");
        Enrollment enrollment = enrollmentDao.get(enrollmentId);
        model.addAttribute("enrollment", enrollment);
        return "enrollment/addEditEnrollment";
    }

    @PostMapping("/admin/academics/enrollments/{enrollmentId}/edit")
    public String editEnrollment(@PathVariable("enrollmentId") int enrollmentId, @ModelAttribute("enrollment") Enrollment enrollment,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Enrollments");
            model.addAttribute("message", "Edit Enrollment");
            model.addAttribute("submessage1", "Edit Enrollment");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/enrollments/" + enrollmentId + "/edit");
            model.addAttribute("edit", "true");
            return "enrollment/addEditEnrollment";
        }
        enrollmentDao.update(enrollment);
        return "redirect:/admin/academics/enrollments";
    }

    @GetMapping("/admin/academics/enrollments/{enrollmentId}/delete")
    public ResponseEntity<Integer> deleteEnrollment(@PathVariable("enrollmentId") int enrollmentId, Model model) {
        enrollmentDao.delete(enrollmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
