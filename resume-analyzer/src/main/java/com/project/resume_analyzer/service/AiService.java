package com.project.resume_analyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${openai.api.key:}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://api.openai.com/v1");

    public String getSuggestions(List<String> missingSkills) {

        if (apiKey == null || apiKey.isEmpty()) {
            return "AI unavailable. Improve these skills: " + String.join(", ", missingSkills);
        }

        String prompt = "Suggest improvements for these missing skills: " + missingSkills;

        try {
            Map<String, Object> requestBody = Map.of(
                    "model", "gpt-4o-mini",
                    "messages", List.of(
                            Map.of("role", "user", "content", prompt)
                    )
            );

            Map response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response == null || response.get("choices") == null) {
                return "AI unavailable. Improve these skills: " + String.join(", ", missingSkills);
            }

            List choices = (List) response.get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map message = (Map) firstChoice.get("message");

            return message.get("content").toString();

        } catch (Exception e) {
            return "AI service busy. Improve these skills: " + String.join(", ", missingSkills);
        }
    }
}