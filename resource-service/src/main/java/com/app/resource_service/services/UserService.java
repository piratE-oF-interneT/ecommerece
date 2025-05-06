package com.app.resource_service.services;


import com.app.resource_service.dto.Coordinates;
import com.app.resource_service.repository.HospitalRepository;
import com.app.resource_service.repository.MobileMedicalCampRepository;
import com.app.resource_service.repository.ReliefCampRepository;
import com.app.resource_service.resources.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MobileMedicalCampRepository mobileMedicalCampRepository;

    @Autowired

    private ReliefCampRepository reliefCampRepository;

    public List<?> findNearByResource(Coordinates coordinates , String type){

        if (type.equals("hospital")){
           return hospitalRepository.findNearby(coordinates.getLatitude() , coordinates.getLongitude(),5.0);
        } else if (type.equals("reliefCamp")) {
            return reliefCampRepository.findNearby(coordinates.getLatitude(),coordinates.getLongitude(),5.0);
        }
        else{
            return  mobileMedicalCampRepository.findNearby(coordinates.getLatitude(), coordinates.getLongitude(), 5.0);
        }

    }
}
