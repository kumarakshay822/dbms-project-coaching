package com.dbms.coaching.validators;

import com.dbms.coaching.dao.ResultDao;
import com.dbms.coaching.models.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResultValidator implements Validator {
    @Autowired
    private ResultDao resultDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Result.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Result result = (Result) target;
        int studentId =  result.getStudent().getStudentId();
        int testId = result.getTestId();

        if (resultDao.get(testId, studentId) != null) {
            errors.rejectValue("student.studentId", "Duplicate.result.studentId");
        }
    }
}
