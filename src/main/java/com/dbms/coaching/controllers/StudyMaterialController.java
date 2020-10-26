package com.dbms.coaching.controllers;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.dbms.coaching.dao.StudyMaterialDao;
import com.dbms.coaching.models.StudyMaterial;
import com.dbms.coaching.services.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudyMaterialController {
    @Autowired
    private StudyMaterialDao studyMaterialDao;

    @Autowired
    private StorageService storageService;

    @GetMapping("/admin/academics/subjects/{subjectId}/materials")
    public String listStudyMaterials(@PathVariable("subjectId") String subjectId, Model model) {
        model.addAttribute("title", "Academic Portal - Study Materials");
        model.addAttribute("message", "View all the study materials");
        List<StudyMaterial> materials = studyMaterialDao.getAllBySubjectId(subjectId);
        model.addAttribute("materials", materials);
        List<Path> urls = new ArrayList<>();
        for (StudyMaterial material : materials) {
            urls.add(storageService.getFileLocation(subjectId, material.getFilename()));
        }
        model.addAttribute("urls", urls);
        return "studymaterial/listMaterials";
    }

    @GetMapping("/admin/academics/subjects/{subjectId}/materials/add")
    public String addStudyMaterial(@PathVariable("subjectId") String subjectId, Model model) {
        model.addAttribute("title", "Academic Portal - Study Materials");
        model.addAttribute("message", "Add Study Material");
        model.addAttribute("submessage1", "Add Study Material");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/materials/add");
        StudyMaterial material= new StudyMaterial();
        model.addAttribute("material", material);
        return "studymaterial/addEditMaterial";
    }

    @PostMapping("/admin/academics/subjects/{subjectId}/materials/add")
    public String addStudyMaterial(@PathVariable("subjectId") String subjectId,
            @ModelAttribute("material") StudyMaterial material, BindingResult bindingResult, Model model,
            @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Study Materials");
            model.addAttribute("message", "Add Study Material");
            model.addAttribute("submessage1", "Add Study Material");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/materials/add");
            return "studymaterial/addEditMaterial";
        }
        material.setFilename(file.getOriginalFilename());
        studyMaterialDao.save(material);
        storageService.save(subjectId, file);
        return "redirect:/admin/academics/subjects/" + subjectId + "/materials";
    }

    @GetMapping("/admin/academics/subjects/{subjectId}/materials/{materialId}")
    public String viewStudyMaterial(@PathVariable("subjectId") String subjectId,
            @PathVariable("materialId") String materialId, Model model) {
        model.addAttribute("title", "Academic Portal - Courses");
        model.addAttribute("message", "View Study Material");
        model.addAttribute("submessage1", "Study Material Details");
        StudyMaterial material= studyMaterialDao.get(subjectId, materialId);
        model.addAttribute("material", material);
        Path url = storageService.getFileLocation(subjectId, material.getFilename());
        model.addAttribute("url", url);
        return "studymaterial/viewMaterial";
    }

    @GetMapping("/admin/academics/subjects/{subjectId}/materials/{materialId}/edit")
    public String editStudyMaterial(@PathVariable("subjectId") String subjectId,
    @PathVariable("materialId") String materialId, Model model) {
        model.addAttribute("title", "Academic Portal - Study Materials");
        model.addAttribute("message", "Edit Study Material");
        model.addAttribute("submessage1", "Edit Study Material");
        model.addAttribute("buttonmessage", "Finish");
        model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/materials/" + materialId + "/edit");
        model.addAttribute("edit", "true");
        StudyMaterial material = studyMaterialDao.get(subjectId, materialId);
        model.addAttribute("material", material);
        Path url = storageService.getFileLocation(subjectId, material.getFilename());
        model.addAttribute("url", url);
        return "studymaterial/addEditMaterial";
    }

    @PostMapping("/admin/academics/subjects/{subjectId}/materials/{materialId}/edit")
    public String editStudyMaterial(@PathVariable("subjectId") String subjectId,
    @PathVariable("materialId") String materialId, @ModelAttribute("material") StudyMaterial material,
    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Academic Portal - Study Materials");
            model.addAttribute("message", "Edit Study Material");
            model.addAttribute("submessage1", "Edit Study Material");
            model.addAttribute("buttonmessage", "Finish");
            model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/materials/" + materialId + "/edit");
            model.addAttribute("edit", "true");
            Path url = storageService.getFileLocation(subjectId, material.getFilename());
            model.addAttribute("url", url);
            return "studymaterial/addEditMaterial";
        }
        studyMaterialDao.update(material);
        return "redirect:/admin/academics/subjects/" + subjectId + "/materials";
    }

    @GetMapping("/admin/academics/subjects/{subjectId}/materials/{materialId}/delete")
    public ResponseEntity<Integer> deleteStudyMaterial(@PathVariable("subjectId") String subjectId,
            @PathVariable("materialId") String materialId, Model model) {
                String filename = studyMaterialDao.getFilename(subjectId, materialId);
                storageService.delete(subjectId, filename);
        studyMaterialDao.delete(subjectId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
