package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.EmployeeDao;
import com.dbms.coaching.dao.GuardianDao;
import com.dbms.coaching.dao.GuardianPhoneNumberDao;
import com.dbms.coaching.dao.StaffDao;
import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.dao.UserPhoneNumberDao;
import com.dbms.coaching.models.Employee;
import com.dbms.coaching.models.Guardian;
import com.dbms.coaching.models.GuardianPhoneNumber;
import com.dbms.coaching.models.Staff;
import com.dbms.coaching.models.User;
import com.dbms.coaching.models.UserPhoneNumber;
import com.dbms.coaching.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class StaffController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private UserPhoneNumberDao userPhoneNumberDao;

    @Autowired
    private GuardianPhoneNumberDao guardianPhoneNumberDao;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/staffs")
    public String staffsPortal(Model model) {
        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "View all the staffs");
        List<Staff> staffs = staffDao.getAll();
        System.out.println(staffs);
        model.addAttribute("staffs", staffs);
        return "staff/staffsPortal";
    }

    @GetMapping("/admin/staffs/add")
    public String addStaff(Model model) {
        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Create Staff's profile");
        model.addAttribute("submessage1", "Add Staff Details");
        model.addAttribute("submessage2", "Step 1: Staff Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/admin/staffs/add");
        Staff staff = new Staff();
        model.addAttribute("staff", staff);
        System.out.println(staff);
        // TODO: Fix basic salary, join date and end date to be null.
        return "staff/addEditStaff";
    }

    @PostMapping("/admin/staffs/add")
    public String addStaff(@ModelAttribute("staff") Staff staff, BindingResult bindingResult, Model model) {
        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Staff Portal");
            model.addAttribute("message", "Create Staff's profile");
            model.addAttribute("submessage1", "Add Staff Details");
            model.addAttribute("submessage2", "Step 1: Staff Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/admin/staffs/add");
            return "staff/addEditStaff";
        }

        Employee employee = staff.getEmployee();
        User user = employee.getUser();
        user.setPassword("password");
        user = userService.save(user);

        employee.setUser(user);
        employee = employeeDao.save(employee);

        staff.setEmployee(employee);
        staff = staffDao.save(staff);

        return "redirect:/admin/staffs/ES" + staff.getStaffId() + "/add-staff-phone";
    }

    @GetMapping("/admin/staffs/ES{employeeId}")
    public String viewStaff(@PathVariable("employeeId") int employeeId, Model model) {
        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "View Staff's profile");
        model.addAttribute("submessage1", "Staff Details");
        Staff staff = staffDao.getByEmployeeId(employeeId);
        int userId = staff.getEmployee().getUser().getUserId();
        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("staff", staff);
        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
        return "staff/viewStaff";
    }

    @GetMapping("/admin/staffs/ES{employeeId}/edit-staff")
    public String editStaff(@PathVariable("employeeId") int employeeId, Model model) {
        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Edit Staff's profile");
        model.addAttribute("submessage1", "Edit Staff Details");
        model.addAttribute("submessage2", "Step 1: Staff Details");
        model.addAttribute("buttonmessage", "Proceed to Step 2");
        model.addAttribute("submiturl", "/admin/staffs/ES" + employeeId + "/edit-staff");
        model.addAttribute("edit", "true");
        Staff staff = staffDao.getByEmployeeId(employeeId);
        model.addAttribute("staff", staff);
        return "staff/addEditStaff";
    }

    @PostMapping("/admin/staffs/ES{employeeId}/edit-staff")
    public String editStaff(@PathVariable("employeeId") int employeeId, @ModelAttribute("staff") Staff staff,
    BindingResult bindingResult, Model model) {
        // TODO: Validate here
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Staff Portal");
            model.addAttribute("message", "Edit Staff's profile");
            model.addAttribute("submessage1", "Edit Staff Details");
            model.addAttribute("submessage2", "Step 1: Staff Details");
            model.addAttribute("buttonmessage", "Proceed to Step 2");
            model.addAttribute("submiturl", "/admin/staffs/ES" + employeeId + "/edit-staff");
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

        userDao.update(user);
        employeeDao.update(employee);
        staffDao.update(staff);
        return "redirect:/admin/staffs/ES{employeeId}/edit-staff-phone";
    }

    @GetMapping("/admin/staffs/ES{employeeId}/add-staff-phone")
    public String addStaffPhone(@PathVariable("employeeId") int employeeId, Model model) {
        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Create Staff's profile");
        model.addAttribute("submessage1", "Add Staff Details");
        model.addAttribute("submessage2", "Step 2: Staff Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        Employee employee = employeeDao.get(employeeId);
        int userId = employee.getUser().getUserId();
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "staff/addEditStaffPhone";
    }

    @GetMapping("/admin/staffs/ES{employeeId}/edit-staff-phone")
    public String editStaffPhone(@PathVariable("employeeId") int employeeId, Model model) {
        model.addAttribute("title", "Staff Portal");
        model.addAttribute("message", "Edit Staff's profile");
        model.addAttribute("submessage1", "Edit Staff Details");
        model.addAttribute("submessage2", "Step 2: Staff Phone Number");
        model.addAttribute("buttonmessage", "Finish");
        Employee employee = employeeDao.get(employeeId);
        int userId = employee.getUser().getUserId();
        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
        model.addAttribute("phoneNumbers", phoneNumbers);
        model.addAttribute("userId", userId);
        return "staff/addEditStaffPhone";
    }

}
