package com.dbms.coaching.validators;

import com.dbms.coaching.models.User;
import com.dbms.coaching.services.UserService;
import com.dbms.coaching.utils.EmailAddressUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailAddressUtil emailAddressUtil;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Validate username
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.user.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.user.username");
        }
        if (userService.findByEmailAddress(user.getEmailAddress()) != null) {
            errors.rejectValue("emailAddress", "Duplicate.user.emailAddress");
        }

        // Validate firstName
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");

        // Validate emailAddress
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "NotEmpty");
        if (!emailAddressUtil.isValidEmailAddress(user.getEmailAddress())) {
            errors.rejectValue("emailAddress", "Invalid.user.emailAddress");
        }

        if (!user.getRole().equals("ROLE_STUDENT") && !user.getRole().equals("ROLE_STAFF") && !user.getRole().equals("ROLE_TEACHER")) {
            errors.rejectValue("role", "Invalid.user.role");
        }

    }
}
