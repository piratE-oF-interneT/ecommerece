package com.app.community_report_service.controller;

import com.app.community_report_service.clients.ImageProcessClient;
import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.dto.RescueRequestDto;
import com.app.community_report_service.dto.RescueResponseDto;
import com.app.community_report_service.entity.Alert;
import com.app.community_report_service.entity.Report;
import com.app.community_report_service.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final String IMAGE_DIR = "/home/kp"; // Update this to your local folder

    @Autowired
    private AdminService adminService;

    @Autowired
    private ImageProcessClient imageProcessClient;

    @GetMapping("/getAll")
    public ResponseEntity<List<Report>> getAllReports(){

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

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        Path imagePath = Paths.get(IMAGE_DIR, filename);

        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Files.readAllBytes(imagePath);
        String contentType = Files.probeContentType(imagePath); // e.g., image/jpeg

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imageBytes);
    }

    @GetMapping("/verify-report")
    public  ResponseEntity<String> verifyReport(@RequestParam(name="id") Long reportId){

//        call feign client

        String response = adminService.analyzeReport(reportId);
        return new ResponseEntity<>(response , HttpStatus.OK);

    }

    @GetMapping("/find/missing/person")

    public ResponseEntity<String> findMissingPerson(@RequestPart MultipartFile image) throws IOException {

        String response = adminService.findMissingPerson(image.getBytes());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/rescue",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<RescueResponseDto> rescuePeople(@RequestParam String location ,
                                                          @RequestPart MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();
        RescueResponseDto rescueResponseDto = adminService.rescue(location , imageBytes);

        return new ResponseEntity<>(rescueResponseDto , HttpStatus.OK);
    }



    @GetMapping("/get/alert")

    public ResponseEntity<List<Alert>> getAlerts(){

        List<Alert> alerts = adminService.getAlerts();

        return new ResponseEntity<>(alerts , HttpStatus.OK);
    }




}
