package com.project.resume_analyzer.service;

import java.util.*;

public class SkillService {
    public static List<String> extractSkills(String text) {

        List<String> skillsList = Arrays.asList(
                "Java", "Spring Boot", "MySQL", "React",
                "Docker", "AWS","Python","C++","Spring","MongoDB","Docker"
                ,"Node.js","Communication","Leadership","TensorFlow"," PyTorch", "Scikit-learn"
                ,"Tableau", "PowerBI", "Matplotlib", "Seaborn"
        );
        Map<String, List<String>> roleSkillsMap = new HashMap<>();

        roleSkillsMap.put("backend developer", Arrays.asList(
                "Java", "Spring Boot", "SQL", "Docker", "API"
        ));

        roleSkillsMap.put("frontend developer", Arrays.asList(
                "React", "JavaScript", "HTML", "CSS"
        ));

        roleSkillsMap.put("full stack developer", Arrays.asList(
                "Java", "Spring Boot", "React", "SQL","JavaScript", "HTML", "CSS"
        ));
        roleSkillsMap.put("Data and AI Specific role",Arrays.asList("MySQL","Python","R","TensorFlow"," PyTorch", "Scikit-learn"
        ,"Tableau", "PowerBI", "Matplotlib", "Seaborn"));

        List<String> extracted = new ArrayList<>();

        for(String skill : skillsList){
            if(text.toLowerCase().contains(skill.toLowerCase())){
                extracted.add(skill);
            }
        }
        for (String role : roleSkillsMap.keySet()) {
            if (text.toLowerCase().contains(role)) {
                extracted.addAll(roleSkillsMap.get(role));
            }
        }
        return extracted;
    }
}
