package com.app.image_processing_service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GeminiConfig {

    @Bean
    public GenerativeModel generativeModel() throws IOException {
        // Load credentials from classpath
        InputStream credentialsStream = new ClassPathResource("disaster.json").getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

        VertexAI vertexAI;
        vertexAI = new VertexAI("hackathon-458919","us-central1",credentials);


        return new GenerativeModel("gemini-1.5-pro", vertexAI);
    }


}