package com.project.resume_analyzer.controller;

import com.project.resume_analyzer.model.AnalysisResult;
import com.project.resume_analyzer.service.ResumeService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload")
    public List<String> upload(@RequestParam("file") MultipartFile file) {
        return resumeService.processResume(file);
    }
    @PostMapping("/match")
    public AnalysisResult match(@RequestBody String jobDescription) {
        return resumeService.matchSkills(jobDescription);
    }
}
