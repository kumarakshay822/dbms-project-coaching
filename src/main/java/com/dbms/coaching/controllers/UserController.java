package com.dbms.coaching.controllers;

import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.dao.UserPhoneNumberDao;
import com.dbms.coaching.models.UserPhoneNumber;

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

    @GetMapping("/admin/users")
    public String usersPortal(Model model) {
        model.addAttribute("title", "Users Portal");
        model.addAttribute("message", "View all the users");
        return "user/usersPortal";
    }

    @GetMapping("/admin/users/{userId}/activate")
    public ResponseEntity<Integer> activateUser(@PathVariable("userId") int userId, Model model) {
        userDao.activate(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/users/{userId}/delete")
    public ResponseEntity<Integer> deleteUser(@PathVariable("userId") int userId, Model model) {
        userDao.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/users/{userId}/phoneNumber/add")
    public ResponseEntity<Integer> addUserPhoneNumber(@PathVariable("userId") int userId,
            @RequestParam String phoneNumber, Model model) {
        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(phoneNumber, userId);
        userPhoneNumberDao.save(userPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/users/{userId}/phoneNumber/delete")
    public ResponseEntity<Integer> deleteUserPhoneNumber(@PathVariable("userId") int userId,
            @RequestParam String phoneNumber, Model model) {
        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(phoneNumber, userId);
        userPhoneNumberDao.delete(userPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
