package com.app.community_report_service.repository;


import com.app.community_report_service.entity.Responder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponderRepository  extends JpaRepository<Responder,Long> {

    Responder findByCategoryAndAvailable(String category, boolean b);

    Responder findByUserId(String userId);
}
