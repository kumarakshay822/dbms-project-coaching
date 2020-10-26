package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.StudyMaterial;

public interface StudyMaterialDao {
    public void save(StudyMaterial material);

    public List<StudyMaterial> getAllBySubjectId(String subjectId);

    public StudyMaterial get(String subjectId, String materialId);

    public String getFilename(String subjectId, String materialId);

    public void update(StudyMaterial material);

    public void delete(String subjectId, String materialId);
}
