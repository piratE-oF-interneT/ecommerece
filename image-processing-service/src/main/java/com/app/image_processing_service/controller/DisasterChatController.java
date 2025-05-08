package com.app.image_processing_service.controller;

import com.app.image_processing_service.service.DisasterChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gemini")
public class DisasterChatController {

    @Autowired
    private DisasterChatService geminiService;



    @GetMapping("/ask")
    public ResponseEntity<String> ask(@RequestParam String query, @RequestParam String location) {
        String prompt = "You are a disaster management assistant.\nLocation: " + location + "\nQuery: " + query;
        return ResponseEntity.ok(geminiService.getDisasterResponse(prompt));
    }
}
