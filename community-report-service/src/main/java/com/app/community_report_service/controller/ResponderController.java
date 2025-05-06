package com.app.community_report_service.controller;


import com.app.community_report_service.services.ResponderService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/responder")
public class ResponderController {

    @Autowired
    private ResponderService responderService;

    @PutMapping("/resolve")
    public ResponseEntity<String> resolverReport(@RequestParam Long reportId,
                                                 @RequestHeader(name = "X-UserId") String userId){

        Long responderId = responderService.findByUserId(userId);

        return new ResponseEntity<>(responderService.ResolveReport(reportId ,responderId ) , HttpStatus.OK);

    }

}
