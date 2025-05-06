package com.app.community_report_service.services;


import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.entity.Report;
import com.app.community_report_service.entity.Responder;
import com.app.community_report_service.enums.Status;
import com.app.community_report_service.repository.ReportRepository;
import com.app.community_report_service.repository.ResponderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<ReportResponse> getAllReports(){

        List<Report> reports = reportRepository.findAll();

        return reports.stream()
                .map(report -> modelMapper.map(report, ReportResponse.class))
                .collect(Collectors.toList());
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

    public void hireResponder(){
        // todo: add new responder
    }

}
