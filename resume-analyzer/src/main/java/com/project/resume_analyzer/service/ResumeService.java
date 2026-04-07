package com.project.resume_analyzer.service;

import com.project.resume_analyzer.model.AnalysisResult;
import com.project.resume_analyzer.util.PdfParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {
    @Autowired
    private AiService AiService;

    private List<String> resumeSkills;
    public List<String> processResume(MultipartFile file) {
        String text = PdfParserUtil.extractText(file);
        resumeSkills = SkillService.extractSkills(text);
        return resumeSkills;
    }
    public AnalysisResult matchSkills(String jobDescription) {

        if(resumeSkills == null){
            throw new RuntimeException("Please upload resume first");
        }

        List<String> jobSkills = SkillService.extractSkills(jobDescription);

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();


        for(String skill : jobSkills){
            if(resumeSkills.contains(skill)){
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        int matchScore = jobSkills.size() == 0 ? 0 :
                (matchedSkills.size() * 100) / jobSkills.size();

        AnalysisResult result = new AnalysisResult();
        String suggestions = AiService.getSuggestions(missingSkills);
        if (jobSkills.isEmpty()) {
            result.setMatchScore(0);
            result.setMatchedSkills(new ArrayList<>());
            result.setMissingSkills(new ArrayList<>());
            result.setResumeSkills(resumeSkills);
            result.setSuggestions("Please provide a detailed job description with skills.");
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
