package com.dbms.coaching.services;

import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.models.User;
import com.dbms.coaching.utils.PasswordGenerator;
import com.dbms.coaching.utils.ServerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    private ServerUtil serverUtil;

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
        // TODO: Prevent creating admin user using this function
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userDao.findByEmailAddress(emailAddress);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User setPasswordActivateUserAndEmail(User user) {
        String password = passwordGenerator.generatePassword(8);
        user.setIsActive(true);
        user.setPassword(password);

        String email = user.getEmailAddress();
        String subject = "Account Creation Successful";
        String message = "";
        message += "Hello " + user.getName() + ",\n\nHere are your details:\nUsername: " + user.getUsername() + "\nPassword: " + user.getPassword();
        message += "\n\nPlease go to " + serverUtil.getHostAddressAndPort() + "/login in order to login.\n\nThank you!";
        emailSendService.sendEmail(email, subject, message);

        return user;
    }

}
