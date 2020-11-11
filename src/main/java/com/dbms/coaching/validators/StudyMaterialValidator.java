package com.dbms.coaching.validators;

import com.dbms.coaching.dao.StudyMaterialDao;
import com.dbms.coaching.models.StudyMaterial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudyMaterialValidator implements Validator {
    @Autowired
    private StudyMaterialDao studyMaterialDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return StudyMaterial.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudyMaterial studyMaterial = (StudyMaterial) target;
        String materialId = studyMaterial.getMaterialId();
        String subjectId = studyMaterial.getSubjectId();

        if (studyMaterialDao.get(subjectId, materialId) != null) {
            errors.rejectValue("materialId", "Duplicate.studyMaterial.materialId");
        }
    }
}
