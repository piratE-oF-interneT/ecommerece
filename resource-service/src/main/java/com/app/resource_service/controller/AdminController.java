package com.app.resource_service.controller;

import com.app.resource_service.dto.HospitalCreationDto;
import com.app.resource_service.dto.MobileMedicalCampCreationDto;
import com.app.resource_service.dto.ReliefCampCreationDto;
import com.app.resource_service.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/resources")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // --- Hospital Operations ---

    @PostMapping("/hospital")
    public ResponseEntity<String> addHospital(@RequestBody HospitalCreationDto dto) {
        String response = adminService.onBoardHospital(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/hospital/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable Long id) {
        String response = adminService.deleteHospital(id);
        return ResponseEntity.ok(response);
    }

    // --- Mobile Medical Camp Operations ---

    @PostMapping("/medical-camp")
    public ResponseEntity<String> addMedicalCamp(@RequestBody MobileMedicalCampCreationDto dto) {
        String response = adminService.onBoardMedicalCamp(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/medical-camp/{id}")
    public ResponseEntity<String> deleteMedicalCamp(@PathVariable Long id) {
        String response = adminService.deleteMedicalCamo(id);
        return ResponseEntity.ok(response);
    }

    // --- Relief Camp Operations ---

    @PostMapping("/relief-camp")
    public ResponseEntity<String> addReliefCamp(@RequestBody ReliefCampCreationDto dto) {
        String response = adminService.onBoardReliefCamp(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/relief-camp/{id}")
    public ResponseEntity<String> deleteReliefCamp(@PathVariable Long id) {
        String response = adminService.deleteReliefCamp(id);
        return ResponseEntity.ok(response);
    }
}
