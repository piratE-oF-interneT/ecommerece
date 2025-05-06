package com.app.community_report_service.controller;

import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ReportResponse>> getAllReports(){

        return new ResponseEntity<>(adminService.getAllReports() , HttpStatus.OK);
    }

    @PutMapping("/assign")
    public ResponseEntity<String> assignTaskToResponder(@RequestParam Long reportId){
        String message = adminService.assignResponder(reportId);

        return new ResponseEntity<>(message , HttpStatus.OK);
    }

    @PutMapping("/reject")
    public ResponseEntity<String> rejectReport(@RequestParam Long reportId){

        return new ResponseEntity<>(adminService.RejectStatus(reportId),HttpStatus.OK);
    }



}
