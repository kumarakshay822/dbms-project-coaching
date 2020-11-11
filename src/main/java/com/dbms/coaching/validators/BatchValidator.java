package com.dbms.coaching.validators;

import com.dbms.coaching.dao.BatchDao;
import com.dbms.coaching.models.Batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BatchValidator implements Validator {
    @Autowired
    private BatchDao batchDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Batch.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Batch batch = (Batch) target;
        String batchId = batch.getBatchId();
        String courseId = batch.getCourse().getCourseId();

        if (batchDao.get(batchId, courseId) != null) {
            errors.rejectValue("batchId", "Duplicate.batch.batchId");
        }
    }
}
