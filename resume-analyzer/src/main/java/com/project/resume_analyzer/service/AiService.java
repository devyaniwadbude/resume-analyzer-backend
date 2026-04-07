package com.project.resume_analyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AiService {
    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://api.openai.com/v1");

    public String getSuggestions(List<String> missingSkills) {

        System.out.println("API KEY: " + apiKey);

        String prompt = "You are a career assistant. Suggest improvements for missing skills: "
                + missingSkills;

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

            System.out.println("FULL RESPONSE: " + response);


            if (response == null || response.get("choices") == null) {
                return "No suggestions available (API issue)";
            }

            List choices = (List) response.get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map message = (Map) firstChoice.get("message");

            return message.get("content").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI service temporarily unavailable. Showing basic suggestions: "
                    + String.join(", ", missingSkills);
        }
    }
}
