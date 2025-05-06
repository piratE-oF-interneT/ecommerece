package com.kp.repository;

import com.kp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);


    User findByEmailAndPassword(String email, String password);;
}
