package com.app.community_report_service.controller;


import com.app.community_report_service.dto.ReportRequest;
import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.services.CommunityService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    private static final String IMAGE_DIR = "/home/kp/app-images/";

    @PostMapping(value = "/post/report", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postReport(
            @RequestPart("reportRequest") ReportRequest reportRequest,
            @RequestPart("image") MultipartFile image,
            @RequestHeader(name = "X-UserId") String userId) throws IOException {

        // Ensure the static/images folder exists
        File dir = new File(IMAGE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Get the original filename of the uploaded image
        String imageName = image.getOriginalFilename();

        // Path to save the image
        Path filePath = Paths.get(IMAGE_DIR, imageName);

        // Write the image bytes to the file
        Files.write(filePath, image.getBytes());

        // Save the relative path for the image in the database
        String imgPath = "/images/" + imageName;

        // Pass the report data and image path to the service
        String response = communityService.submitReport(reportRequest, image, imgPath, userId);

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/reports")
    public ResponseEntity<List<ReportResponse>> getAllReports(@RequestHeader("X-UserId") String userId){

        List<ReportResponse> responses = communityService.fetchSubmissions(userId);

        return new ResponseEntity<>(responses , HttpStatus.OK);
    }
}
