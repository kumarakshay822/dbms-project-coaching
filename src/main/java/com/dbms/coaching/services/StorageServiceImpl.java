package com.dbms.coaching.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class StorageServiceImpl implements StorageService {

    private final Path uploadDirectory = Paths.get("src", "main", "resources", "static", "uploads");

    @Override
    public void save(String subjectId, MultipartFile file) {
        try {
            Path uploadLocation = this.uploadDirectory.resolve(subjectId);
            Files.createDirectories(uploadLocation);

            Files.copy(file.getInputStream(), uploadLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Unable to save the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Path getFileLocation(String subjectId, String filename) {
        Path path = Paths.get("uploads", subjectId, filename);
        return path;
    }

    @Override
    public void delete(String subjectId, String filename) {
        try {
            Path uploadLocation = this.uploadDirectory.resolve(subjectId);

            Path file = uploadLocation.resolve(filename);
            Files.delete(file);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
