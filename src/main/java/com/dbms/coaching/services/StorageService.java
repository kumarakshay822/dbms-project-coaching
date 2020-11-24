package com.dbms.coaching.services;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public void save(String subjectId, MultipartFile file);

    public String getFileLocation(String subjectId, String filename);

    public Path getFilePath(String subjectId, String filename);

    public void delete(String subjectId, String filename);
}
