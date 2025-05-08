package com.app.image_processing_service.controller;

import com.app.image_processing_service.dto.MissingPersonDo;
import com.app.image_processing_service.service.GeminiImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiImageService geminiService;

    public GeminiController(GeminiImageService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam(value = "prompt", defaultValue = "Describe this image") String prompt) {
        try {
            String result = geminiService.analyzeImage(image.getBytes(), prompt);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing image: " + e.getMessage());
        }
    }

    @PostMapping("/compare")
    public ResponseEntity<Map<String, Boolean>> compareImages(
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2) {
        try {
            boolean result = geminiService.compareImages(
                    image1.getBytes(),
                    image2.getBytes()
            );
            return ResponseEntity.ok(Map.of("match", result));
        } catch (IOException e) {
            return ResponseEntity.status(500)
                    .body(Map.of("match", false));
        }
    }

    @PostMapping("/report/analyze")
    public String analyzeReport(
            @RequestBody byte[] imageBytes,
            @RequestParam(value = "prompt", defaultValue = "Describe this image") String prompt) {
        return geminiService.analyzeImage(imageBytes, prompt);
    }

    @GetMapping("/find/person")
    public boolean findMissingPerson(
            @RequestBody MissingPersonDo missingPersonDo){

        return geminiService.compareImages(missingPersonDo.getImage1(),missingPersonDo.getImage2());
    }

    @PostMapping("/rescue")
    public String rescue(@RequestBody byte[] image , String prompt){
       String response =  geminiService.analyzeImage(image,prompt);

       return response;
    }
}