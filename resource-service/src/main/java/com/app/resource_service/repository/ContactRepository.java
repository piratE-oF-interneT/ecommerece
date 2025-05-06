package com.app.resource_service.repository;

import com.app.resource_service.resources.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact , Long> {
}
