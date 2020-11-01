package com.dbms.coaching.controllers;

import java.util.List;

import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.dao.UserPhoneNumberDao;
import com.dbms.coaching.models.User;
import com.dbms.coaching.models.UserPhoneNumber;
import com.dbms.coaching.services.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPhoneNumberDao userPhoneNumberDao;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/admin/users")
    public String usersPortal(Model model) {
        model.addAttribute("title", "Users Portal");
        model.addAttribute("message", "View all the users");
        List<User> users = userDao.getAll();
        model.addAttribute("users", users);
        return "user/usersPortal";
    }

    @GetMapping("/admin/users/{userId}/activate")
    public ResponseEntity<Integer> activateUser(@PathVariable("userId") int userId, Model model) {
        userDao.activate(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/users/{userId}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId, Model model) {
        if (userId == securityService.findLoggedInUserId()) {
            return new ResponseEntity<>("Can't delete own user", HttpStatus.BAD_REQUEST);
        }
        userDao.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/profile/users/{userId}/phoneNumber/add")
    public ResponseEntity<String> addUserPhoneNumber(@PathVariable("userId") int userId,
            @RequestParam String phoneNumber, Model model) {
        int loggedInUserId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("admin") && loggedInUserId != userId)
            return new ResponseEntity<>("You are not allowed to perform this action", HttpStatus.BAD_REQUEST);

        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(phoneNumber, userId);
        userPhoneNumberDao.save(userPhoneNumber);
        // TODO: Check for Validation
        // if (false) {
        //     return new ResponseEntity<>("Wrong Phone Number", HttpStatus.BAD_REQUEST);
        // }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/profile/users/{userId}/phoneNumber/delete")
    public ResponseEntity<String> deleteUserPhoneNumber(@PathVariable("userId") int userId,
            @RequestParam String phoneNumber, Model model) {
        int loggedInUserId = securityService.findLoggedInUserId();
        String role = securityService.findLoggedInUserRole();
        if (!role.equals("admin") && loggedInUserId != userId)
            return new ResponseEntity<>("You are not allowed to perform this action", HttpStatus.BAD_REQUEST);

        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(phoneNumber, userId);
        userPhoneNumberDao.delete(userPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
