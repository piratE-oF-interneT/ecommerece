package com.app.community_report_service.services;


import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.ecceptions.ResourceNotFoundException;
import com.app.community_report_service.entity.Report;
import com.app.community_report_service.entity.Responder;
import com.app.community_report_service.enums.Status;
import com.app.community_report_service.repository.ReportRepository;
import com.app.community_report_service.repository.ResponderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponderService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ResponderRepository responderRepository;


    @Transactional
    public String ResolveReport(Long reportId , Long responderId){

        Report report = reportRepository.findById(reportId).orElseThrow(()-> new ResourceNotFoundException("report not found with this id"));

        Responder responder = responderRepository.findById(responderId).orElseThrow(() -> new ResourceNotFoundException("responder not found with id"));
        report.setStatus(Status.RESOLVED);
        responder.setAvailable(true);

        reportRepository.save(report);
        responderRepository.save(responder);


        return "successfully resolved the report : "+ report.getTitle();

    }

    public Long findByUserId(String userId){

        Responder responder = responderRepository.findByUserId(userId);

        return responder.getId();
    }
}
