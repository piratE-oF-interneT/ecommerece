package com.app.community_report_service.repository;

import com.app.community_report_service.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlertRepository extends JpaRepository<Alert , Long> {
    List<Alert> findAllByIsAlerted(boolean isAlerted);
}
