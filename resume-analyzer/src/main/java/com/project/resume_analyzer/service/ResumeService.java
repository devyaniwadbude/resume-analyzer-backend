package com.project.resume_analyzer.service;

import com.project.resume_analyzer.model.AnalysisResult;
import com.project.resume_analyzer.util.PdfParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    @Autowired
    private AiService aiService;

    private List<String> resumeSkills;

    public List<String> processResume(MultipartFile file) {
        String text = PdfParserUtil.extractText(file);

        resumeSkills = SkillService.extractSkills(text)
                .stream()
                .map(String::toLowerCase)
                .distinct()
                .toList();

        return resumeSkills;
    }

    public AnalysisResult matchSkills(String jobDescription) {

        if (resumeSkills == null) {
            throw new RuntimeException("Please upload resume first");
        }

        // 🔥 Clean job skills
        List<String> jobSkills = SkillService.extractSkills(jobDescription)
                .stream()
                .map(String::toLowerCase)
                .distinct()
                .toList();

        Set<String> matchedSkills = new HashSet<>();
        Set<String> missingSkills = new HashSet<>();

        for (String skill : jobSkills) {
            if (resumeSkills.contains(skill)) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        int matchScore = jobSkills.size() == 0 ? 0 :
                (matchedSkills.size() * 100) / jobSkills.size();

        AnalysisResult result = new AnalysisResult();

        // 🔥 ROLE DETECTION
        String role = jobDescription.toLowerCase();

        if (role.contains("backend")) result.setDetectedRole("Backend Developer");
        else if (role.contains("frontend")) result.setDetectedRole("Frontend Developer");
        else if (role.contains("fullstack")) result.setDetectedRole("Full Stack Developer");
        else result.setDetectedRole("General Developer");

        // 🔥 AI Suggestions
        String suggestions = aiService.getSuggestions(new ArrayList<>(missingSkills));

        if (jobSkills.isEmpty()) {
            result.setMatchScore(0);
            result.setMatchedSkills(new ArrayList<>());
            result.setMissingSkills(new ArrayList<>());
            result.setResumeSkills(resumeSkills);
            result.setSuggestions("Please provide a detailed job description.");
            return result;
        }

        result.setMatchScore(matchScore);
        result.setMatchedSkills(matchedSkills);
        result.setMissingSkills(missingSkills);
        result.setResumeSkills(resumeSkills);
        result.setSuggestions(suggestions);

        return result;
    }
}