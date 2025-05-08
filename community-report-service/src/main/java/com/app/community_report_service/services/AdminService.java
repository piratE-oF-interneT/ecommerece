package com.app.community_report_service.services;


import com.app.community_report_service.clients.ImageProcessClient;
import com.app.community_report_service.dto.MissingPersonDto;
import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.dto.RescueRequestDto;
import com.app.community_report_service.dto.RescueResponseDto;
import com.app.community_report_service.ecceptions.ResourceNotFoundException;
import com.app.community_report_service.ecceptions.ResponderBusyException;
import com.app.community_report_service.entity.Alert;
import com.app.community_report_service.entity.Person;
import com.app.community_report_service.entity.Report;
import com.app.community_report_service.entity.Responder;
import com.app.community_report_service.enums.Status;
import com.app.community_report_service.repository.AlertRepository;
import com.app.community_report_service.repository.PersonRepository;
import com.app.community_report_service.repository.ReportRepository;
import com.app.community_report_service.repository.ResponderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ResponderRepository responderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageProcessClient imageProcessClient;

    @Autowired
    private PersonRepository personRepository;

    @Autowired

    private AlertRepository alertRepository;

    public List<Report> getAllReports(){

        List<Report> reports = reportRepository.findAll();

        return reports;
    }

    public String RejectStatus(Long reportId){

        Report report = reportRepository.findById(reportId).orElseThrow(()-> new ResourceNotFoundException("report not found with this id"));

        report.setStatus(Status.REJECTED);

        reportRepository.save(report);

        return "status rejected successfully";

    }

    public String assignResponder(Long reportId){

        Report report = reportRepository.findById(reportId).orElseThrow(()-> new ResourceNotFoundException("report not found with this id"));

        String category = report.getCategory();

        Responder availableResponder = responderRepository.findByCategoryAndAvailable(category,true);

        if (availableResponder == null){
            throw  new ResponderBusyException("current responder is busy , try later");
        }

        availableResponder.getReports().add(report);
        report.setStatus(Status.VERIFIED);

        availableResponder.setAvailable(false);

        reportRepository.save(report);
        responderRepository.save(availableResponder);


        return "successfully assigned task to responder : "+availableResponder.getName();



    }

    public String analyzeReport(Long reportId){
        Report report = reportRepository.findById(reportId).orElseThrow(()-> new ResourceNotFoundException("report not found with this id"));

        byte[] imageBytes = report.getImageBytes();

        String prompt = "validate this images with title : "+report.getTitle()+"and description : "+report.getDescription();

        return imageProcessClient.analyzeReport(imageBytes,prompt);
    }

    public void hireResponder(){
        // todo: add new responder
    }

    public String findMissingPerson(byte[] image1) {

        List<Person> persons = personRepository.findAll();

        for(Person person : persons){

//      todo:      make feign client api call to image processing service

            byte[] image2 = person.getImageBytes();

            MissingPersonDto missingPersonDto = new MissingPersonDto();
            missingPersonDto.setImage1(image1);
            missingPersonDto.setImage2(image2);

            boolean response = imageProcessClient.findMissingPerson(missingPersonDto);

            if (response){
                return "person found with name"+person.getName()+"at "+person.getCamp()+" camp";
            }

        }
        return "no match found with this person";
    }

    public RescueResponseDto rescue(String location , byte[] image) throws IOException {
        String prompt = "check if human are there in image and if present then give the count of people";


        String message = imageProcessClient.rescue(image , prompt);

        RescueResponseDto rescueResponseDto = new RescueResponseDto();
        rescueResponseDto.setMessage(message);
        rescueResponseDto.setLocation(location);

        return  rescueResponseDto;


    }

    public List<Alert> getAlerts() {

        return alertRepository.findAllByIsAlerted(false);
    }
}
