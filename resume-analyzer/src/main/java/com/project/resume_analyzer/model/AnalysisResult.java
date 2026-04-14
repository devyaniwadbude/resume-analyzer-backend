package com.project.resume_analyzer.model;

import java.util.*;

public class AnalysisResult {
    private int matchScore;
    private Set<String> matchedSkills = new LinkedHashSet<>();
    private Set<String> missingSkills = new LinkedHashSet<>();
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
        return new ArrayList<>(missingSkills);
    }

    public void setMissingSkills(Collection<String> missingSkills) {
        this.missingSkills = new LinkedHashSet<>(missingSkills); // removes duplicates
    }

    public List<String> getMatchedSkills() {
        return new ArrayList<>(matchedSkills);
    }

    public void setMatchedSkills(Collection<String> matchedSkills) {
        this.matchedSkills = new LinkedHashSet<>(matchedSkills); // removes duplicates
    }
}