package com.app.community_report_service.controller;


import com.app.community_report_service.dto.ReportRequest;
import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.services.CommunityService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;



    @PostMapping("/post/report")
    public ResponseEntity<String> postReport(@RequestBody ReportRequest reportRequest ,
                                             @RequestPart MultipartFile image,
                                             @RequestHeader(name = "X-UserId") String userId){
        String response = communityService.submitReport(reportRequest,image,userId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<ReportResponse>> getAllReports(@RequestHeader("X-UserId") String userId){

        List<ReportResponse> responses = communityService.fetchSubmissions(userId);

        return new ResponseEntity<>(responses , HttpStatus.OK);
    }
}
