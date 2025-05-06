package com.kp.controller;

import com.kp.dto.AuthResponseDto;
import com.kp.dto.LoginRequestDto;
import com.kp.dto.RegisterDto;
import com.kp.dto.UserProfileDto;
import com.kp.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
//@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody RegisterDto registerDto) {
        AuthResponseDto response = authService.registerUser(registerDto);
        return ResponseEntity.ok(response); // 200 OK with token
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        AuthResponseDto response = authService.loginUser(loginRequestDto);
        return ResponseEntity.ok(response); // 200 OK with token
    }

    @GetMapping(path = "/profile",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDto> getProfile(@RequestHeader(name = "X-UserId") String userId){

        log.info("x-userid : {}",userId);

        UserProfileDto responseProfile = authService.getProfile(userId);

        return new ResponseEntity<>(responseProfile , HttpStatus.OK);
    }
}
