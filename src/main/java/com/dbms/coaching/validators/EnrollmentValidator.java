package com.dbms.coaching.validators;

import com.dbms.coaching.dao.EnrollmentDao;
import com.dbms.coaching.models.Enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EnrollmentValidator implements Validator {
    @Autowired
    private EnrollmentDao enrollmentDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Enrollment.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Enrollment enrollment = (Enrollment) target;
        int studentId = enrollment.getStudentId();
        String courseId = enrollment.getCourseId();

        if (enrollmentDao.getByStudentAndCourse(studentId, courseId) != null) {
            errors.rejectValue("studentId", "Duplicate.enrollment.studentId");
        }
    }
}
