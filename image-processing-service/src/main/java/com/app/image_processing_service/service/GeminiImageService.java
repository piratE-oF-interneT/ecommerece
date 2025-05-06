package com.app.image_processing_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class GeminiImageService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.model:gemini-1.5-flash}") // Default to 1.5 Flash
    private String modelName;

    @Autowired
    private RestTemplate restTemplate;

    public String analyzeImage(byte[] imageBytes, String prompt) {
        try {
            // 1. Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // 2. Build the request body
            String requestBody = buildRequestBody(imageBytes, prompt);

            // 3. Make the API call
            String url = String.format("%s/models/%s:generateContent?key=%s",
                    apiUrl, modelName, apiKey);

            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // 4. Parse and return the response
            return parseResponse(response.getBody());

        } catch (Exception e) {
            throw new RuntimeException("Failed to analyze image: " + e.getMessage(), e);
        }
    }

    private String buildRequestBody(byte[] imageBytes, String prompt) {
        return """
            {
              "contents": [
                {
                  "parts": [
                    {"text": "%s"},
                    {
                      "inline_data": {
                        "mime_type": "image/jpeg",
                        "data": "%s"
                      }
                    }
                  ]
                }
              ],
              "generationConfig": {
                "temperature": 0.4,
                "topP": 1,
                "topK": 32,
                "maxOutputTokens": 4096
              }
            }
            """.formatted(prompt, Base64.getEncoder().encodeToString(imageBytes));
    }

    private String parseResponse(String responseBody) {
        // Simple extraction of the first candidate's text
        try {
            return responseBody.split("\"text\": \"")[1].split("\"")[0];
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response: " + responseBody, e);
        }
    }


//    ----------------------------------------------------------------------------------------------------

        public boolean compareImages(byte[] image1Bytes, byte[] image2Bytes) {
            try {
                // 1. Prepare the comparison prompt
                String prompt = """
                Analyze these two images and determine if they are same of same person then return true.
                """;

                // 2. Build the request with both images
                String requestBody = buildComparisonRequest(image1Bytes, image2Bytes, prompt);

                // 3. Make the API call
                String url = String.format("%s/models/%s:generateContent?key=%s",
                        apiUrl, modelName, apiKey);

                HttpEntity<String> request = new HttpEntity<>(requestBody, headers());
                ResponseEntity<String> response = restTemplate.exchange(
                        url, HttpMethod.POST, request, String.class);

                // 4. Parse the boolean response
                return parseBooleanResponse(response.getBody());

            } catch (Exception e) {
                throw new RuntimeException("Image comparison failed: " + e.getMessage(), e);
            }
        }

        private HttpHeaders headers() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            return headers;
        }

        private String buildComparisonRequest(byte[] image1, byte[] image2, String prompt) {
            return String.format("""
            {
              "contents": [
                {
                  "parts": [
                    {"text": "%s"},
                    {
                      "inline_data": {
                        "mime_type": "image/jpeg",
                        "data": "%s"
                      }
                    },
                    {
                      "inline_data": {
                        "mime_type": "image/jpeg",
                        "data": "%s"
                      }
                    }
                  ]
                }
              ],
              "generationConfig": {
                "maxOutputTokens": 10
              }
            }
            """,
                    prompt,
                    Base64.getEncoder().encodeToString(image1),
                    Base64.getEncoder().encodeToString(image2));
        }

        private boolean parseBooleanResponse(String response) {
            String cleanResponse = response.toLowerCase().trim();
            if (cleanResponse.contains("true")) {
                return true;
            } else if (cleanResponse.contains("false")) {
                return false;
            }
            throw new RuntimeException("Unexpected response format: " + response);
        }
    }
