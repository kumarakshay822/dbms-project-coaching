package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.EmployeeDao;
import com.dbms.coaching.dao.StaffDao;
import com.dbms.coaching.dao.SubjectDao;
import com.dbms.coaching.dao.TeacherDao;
import com.dbms.coaching.dao.UserPhoneNumberDao;
import com.dbms.coaching.models.Employee;
import com.dbms.coaching.models.Staff;
import com.dbms.coaching.models.Subject;
import com.dbms.coaching.models.Teacher;
import com.dbms.coaching.models.User;
import com.dbms.coaching.models.UserPhoneNumber;
import com.dbms.coaching.services.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private UserPhoneNumberDao userPhoneNumberDao;

    @GetMapping("/profile")
    public String redirectToProfile(Model model) {
        String role = securityService.findLoggedInUserRole();
        if (role.equals("student")) {
            return "redirect:/profile/student";
        }
        else if (role.equals("teacher")) {
            return "redirect:/profile/teacher";
        }
        else if (role.equals("staff")) {
            return "redirect:/profile/staff";
        }
        else {
            return "redirect:/profile/admin";
        }
    }

    @GetMapping("/profile/teacher/add")
    public String addTeacher(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("teacher"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId != null)
            return "redirect:/profile";

        model.addAttribute("title", "Teacher Portal");
        model.addAttribute("message", "Create Teacher's profile");
        model.addAttribute("submessage1", "Add Teacher Details");
        model.addAttribute("submessage2", "Step 1: Teacher Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/profile/teacher/add");
        List<Subject> subjects = subjectDao.getAll();
        model.addAttribute("subjects", subjects);

        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        // TODO: Fix basic salary, join date and end date to be null.
        return "teacher/addEditTeacher";
    }

    @PostMapping("/profile/teacher/add")
    public String addTeacher(@ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("teacher"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId != null)
            return "redirect:/profile";

        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Teacher Portal");
            model.addAttribute("message", "Create Teacher's profile");
            model.addAttribute("submessage1", "Add Teacher Details");
            model.addAttribute("submessage2", "Step 1: Teacher Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/profile/teacher/add");
            List<Subject> subjects = subjectDao.getAll();
            model.addAttribute("subjects", subjects);
            return "teacher/addEditTeacher";
        }
        Employee employee = teacher.getEmployee();
        User user = employee.getUser();
        user.setUserId(userId);

        employee.setUser(user);
        employee = employeeDao.save(employee);

        teacher.setEmployee(employee);
        teacher = teacherDao.save(teacher);

        return "redirect:/profile/teacher/add-teacher-phone";
    }

    @GetMapping("/profile/teacher")
    public String viewTeacher(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("teacher"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/teacher/add";

        model.addAttribute("title", "Teacher Portal");
        model.addAttribute("message", "View Teacher's profile");
        model.addAttribute("submessage1", "Teacher Details");
        Teacher teacher = teacherDao.getByEmployeeId(employeeId);
        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("teacher", teacher);
        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
        return "teacher/viewTeacher";
    }

    @GetMapping("/profile/teacher/edit-teacher")
    public String editTeacher(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("teacher"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/teacher/add";

        model.addAttribute("title", "Teacher Portal");
        model.addAttribute("message", "Edit Teacher's profile");
        model.addAttribute("submessage1", "Edit Teacher Details");
        model.addAttribute("submessage2", "Step 1: Teacher Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/profile/teacher/edit-teacher");
        model.addAttribute("edit", "true");
        List<Subject> subjects = subjectDao.getAll();
        model.addAttribute("subjects", subjects);

        Teacher teacher = teacherDao.getByEmployeeId(employeeId);
        model.addAttribute("teacher", teacher);
        return "teacher/addEditTeacher";
    }

    @PostMapping("/profile/teacher/edit-teacher")
    public String editTeacher(@ModelAttribute("teacher") Teacher teacher,
            BindingResult bindingResult, Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("teacher"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/teacher/add";

        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Teacher Portal");
            model.addAttribute("message", "Edit Teacher's profile");
            model.addAttribute("submessage1", "Edit Teacher Details");
            model.addAttribute("submessage2", "Step 1: Teacher Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/profile/teacher/edit-teacher");
            model.addAttribute("edit", "true");
            return "teacher/addEditTeacher";
        }
        Teacher oldTeacher = teacherDao.getByEmployeeId(employeeId);

        Employee employee = teacher.getEmployee();
        User user = employee.getUser();
        user.setUserId(oldTeacher.getEmployee().getUser().getUserId());

        employee.setUser(user);
        employee.setEmployeeId(oldTeacher.getEmployee().getEmployeeId());
        teacher.setEmployee(employee);
        teacher.setTeacherId(oldTeacher.getTeacherId());
        teacher.setSubject(oldTeacher.getSubject());

        employeeDao.updateOwnProfile(employee);
        teacherDao.update(teacher);
        return "redirect:/profile/teacher/edit-teacher-phone";
    }

    @GetMapping("/profile/teacher/add-teacher-phone")
    public String addTeacherPhone(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("teacher"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/teacher/add";

        model.addAttribute("title", "Teacher Portal");
        model.addAttribute("message", "Create Teacher's profile");
        model.addAttribute("submessage1", "Add Teacher Details");
        model.addAttribute("submessage2", "Step 2: Teacher Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "teacher/addEditTeacherPhone";
    }

    @GetMapping("/profile/teacher/edit-teacher-phone")
    public String editTeacherPhone(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("teacher"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/teacher/add";

        model.addAttribute("title", "Teacher Portal");
        model.addAttribute("message", "Edit Teacher's profile");
        model.addAttribute("submessage1", "Edit Teacher Details");
        model.addAttribute("submessage2", "Step 2: Teacher Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "teacher/addEditTeacherPhone";
    }

    @GetMapping("/profile/staff/add")
    public String addStaff(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("staff"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId != null)
            return "redirect:/profile";

        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Create Staff's profile");
        model.addAttribute("submessage1", "Add Staff Details");
        model.addAttribute("submessage2", "Step 1: Staff Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/profile/staff/add");
        Staff staff = new Staff();
        model.addAttribute("staff", staff);
        // TODO: Fix basic salary, join date and end date to be null.
        return "staff/addEditStaff";
    }

    @PostMapping("/profile/staff/add")
    public String addStaff(@ModelAttribute("staff") Staff staff, BindingResult bindingResult, Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("staff"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId != null)
            return "redirect:/profile";

        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Staff Portal");
            model.addAttribute("message", "Create Staff's profile");
            model.addAttribute("submessage1", "Add Staff Details");
            model.addAttribute("submessage2", "Step 1: Staff Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/profile/staff/add");
            return "staff/addEditStaff";
        }

        Employee employee = staff.getEmployee();
        User user = employee.getUser();
        user.setUserId(userId);

        employee.setUser(user);
        employee = employeeDao.save(employee);

        staff.setEmployee(employee);
        staff = staffDao.save(staff);

        return "redirect:/profile/staff/add-staff-phone";
    }

    @GetMapping("/profile/staff")
    public String viewStaff(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("staff"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/staff/add";

        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "View Staff's profile");
        model.addAttribute("submessage1", "Staff Details");
        Staff staff = staffDao.getByEmployeeId(employeeId);
        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("staff", staff);
        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
        return "staff/viewStaff";
    }

    @GetMapping("/profile/staff/edit-staff")
    public String editStaff(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("staff"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/staff/add";

        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Edit Staff's profile");
        model.addAttribute("submessage1", "Edit Staff Details");
        model.addAttribute("submessage2", "Step 1: Staff Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/profile/staff/edit-staff");
        model.addAttribute("edit", "true");
        Staff staff = staffDao.getByEmployeeId(employeeId);
        model.addAttribute("staff", staff);
        return "staff/addEditStaff";
    }

    @PostMapping("/profile/staff/edit-staff")
    public String editStaff(@ModelAttribute("staff") Staff staff,
            BindingResult bindingResult, Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("staff"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/staff/add";

        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Staff Portal");
            model.addAttribute("message", "Edit Staff's profile");
            model.addAttribute("submessage1", "Edit Staff Details");
            model.addAttribute("submessage2", "Step 1: Staff Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/profile/staff/edit-staff");
            model.addAttribute("edit", "true");
            return "staff/addEditStaff";
        }
        Staff oldStaff = staffDao.getByEmployeeId(employeeId);

        Employee employee = staff.getEmployee();
        User user = employee.getUser();
        user.setUserId(oldStaff.getEmployee().getUser().getUserId());

        employee.setUser(user);
        employee.setEmployeeId(oldStaff.getEmployee().getEmployeeId());
        staff.setEmployee(employee);
        staff.setStaffId(oldStaff.getStaffId());

        employeeDao.updateOwnProfile(employee);
        staffDao.update(staff);
        return "redirect:/profile/staff/edit-staff-phone";
    }

    @GetMapping("/profile/staff/add-staff-phone")
    public String addStaffPhone(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("staff"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/staff/add";

        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Create Staff's profile");
        model.addAttribute("submessage1", "Add Staff Details");
        model.addAttribute("submessage2", "Step 2: Staff Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "staff/addEditStaffPhone";
    }

    @GetMapping("/profile/staff/edit-staff-phone")
    public String editStaffPhone(Model model) {
        int userId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("staff"))
            return "redirect:/profile";
        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
        if (employeeId == null)
            return "redirect:/profile/staff/add";

        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Edit Staff's profile");
        model.addAttribute("submessage1", "Edit Staff Details");
        model.addAttribute("submessage2", "Step 2: Staff Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "staff/addEditStaffPhone";
    }

}
