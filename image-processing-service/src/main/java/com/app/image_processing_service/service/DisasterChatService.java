package com.app.image_processing_service.service;

import com.app.image_processing_service.dto.ChatRequestDto;
import com.app.image_processing_service.dto.ChatResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisasterChatService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${gemini.api.url}")
    private String geminiUrl;

    @Value("${gemini.api.key}")
    private String apiKey;

    public DisasterChatService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ChatResponseDto processEmergency(ChatRequestDto request) throws JsonProcessingException {
        // 1. Build structured prompt
        String prompt = buildPrompt(request);

        // 2. Call Gemini API
        String jsonResponse = callGeminiApi(prompt);

        // 3. Parse and return response
        return parseGeminiResponse(jsonResponse);
    }

    private String buildPrompt(ChatRequestDto request) {
        return String.format("""
            You are a disaster response AI. For this scenario:
            Disaster Type: %s
            Location: %s
            Details: %s
            
            Provide response in this exact JSON format:
            {
              "botReply": "summary of the situation",
              "severity": "low/medium/high/critical",
              "actions": ["action1", "action2", "action3"],
              "contacts": "contact information"
            }
            """,
                request.getDisasterType(),
                request.getCoordinates(),
                request.getMessage()
        );
    }

    private String callGeminiApi(String prompt) {
        try {
            String requestBody = String.format("""
                {
                  "contents": [{
                    "parts": [{"text": "%s"}]
                  }]
                }""", prompt.replace("\"", "\\\""));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-goog-api-key", apiKey);

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    geminiUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("API request failed with status: " + response.getStatusCode());
            }

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to call Gemini API", e);
        }
    }

    private ChatResponseDto parseGeminiResponse(String jsonResponse) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode content = root.path("candidates").get(0).path("content").path("parts").get(0).path("text");
        JsonNode responseNode = objectMapper.readTree(content.asText());

        // Convert actions array to List<String>
        List<String> actions = new ArrayList<>();
        responseNode.path("actions").forEach(node -> actions.add(node.asText()));

        return new ChatResponseDto(
                responseNode.path("botReply").asText(),
                responseNode.path("severity").asText(),
                actions,
                responseNode.path("contacts").asText()
        );
    }
}