package com.app.community_report_service.clients;


import com.app.community_report_service.dto.MissingPersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "image-processing-service")
@Service
public interface ImageProcessClient {


    @PostMapping("/api/gemini/report/analyze")
    public String analyzeReport(
            @RequestBody byte[] imageBytes,
            @RequestParam(value = "prompt", defaultValue = "Describe this image") String prompt);



    @GetMapping("/api/gemini/find/person")
    public boolean findMissingPerson(
           @RequestBody MissingPersonDto missingPersonDto);

    @PostMapping("/api/gemini/rescue")
    public String rescue(@RequestBody byte[] image ,@RequestParam String prompt);


}
