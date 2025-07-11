package com.app.community_report_service.repository;

import com.app.community_report_service.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report , Long> {
    List<Report> findAllByReporterId(String reporterId);



}
