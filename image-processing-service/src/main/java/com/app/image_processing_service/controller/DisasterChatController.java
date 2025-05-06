package com.app.image_processing_service.controller;

import com.app.image_processing_service.dto.ChatRequestDto;
import com.app.image_processing_service.dto.ChatResponseDto;
import com.app.image_processing_service.service.DisasterChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disaster-chat")
@RequiredArgsConstructor
public class DisasterChatController {

    private final DisasterChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponseDto> handleDisasterQuery(
            @Valid @RequestBody ChatRequestDto request) throws JsonProcessingException {
        return ResponseEntity.ok(chatService.processEmergency(request));
    }
}