package com.project.resume_analyzer.model;

import java.util.List;

public class AnalysisResult {
    private int matchScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private List<String> resumeSkills;
    private String suggestions;
    private String detectedRole;

    public String getDetectedRole() {
        return detectedRole;
    }

    public void setDetectedRole(String detectedRole) {
        this.detectedRole = detectedRole;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public List<String> getResumeSkills() {
        return resumeSkills;
    }

    public void setResumeSkills(List<String> resumeSkills) {
        this.resumeSkills = resumeSkills;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }



    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }



    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }
}
