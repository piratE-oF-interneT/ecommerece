package com.app.community_report_service.services;


import com.app.community_report_service.dto.ReportRequest;
import com.app.community_report_service.dto.ReportResponse;
import com.app.community_report_service.entity.Report;
import com.app.community_report_service.enums.Status;
import com.app.community_report_service.repository.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommunityService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReportRepository reportRepository;

    @Transactional
    public String submitReport(ReportRequest reportRequest , MultipartFile image , String reportedId) throws IOException {
        String imagePath;
        try {
            imagePath = saveImage(image,"uploads/images");

        }
        catch (IOException ioException){
            throw new InternalServerException("internal server error");
        }

        Report report = modelMapper.map(reportRequest, Report.class);
        report.setImageUrl(imagePath);
        report.setStatus(Status.PENDING);
        report.setReporterId(reportedId);

        reportRepository.save(report);

//        todo: send notification to user regarding successfull submission

//        todo: send push notification to admin about report

        return "successfully submitted report.";

    }
    public List<ReportResponse> fetchSubmissions(String reporterId){

        List<Report> reports = reportRepository.findAllByReporterId(reporterId);

        return reports.stream()
                .map(report -> modelMapper.map(report,ReportResponse.class))
                .collect(Collectors.toList());
    }



    private String saveImage(MultipartFile file, String uploadDir) throws  IOException {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFilename = UUID.randomUUID() + "-" + originalFilename;

        Path uploadPath = Paths.get(uploadDir);
        Files.createDirectories(uploadPath); // Create folder if not exist

        Path filePath = uploadPath.resolve(uniqueFilename);
        file.transferTo(filePath.toFile()); // Save file

        return filePath.toString(); // Return saved file path
    }

}
