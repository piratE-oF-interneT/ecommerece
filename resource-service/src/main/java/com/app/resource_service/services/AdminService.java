package com.app.resource_service.services;


import com.app.resource_service.configs.GeometryUtils;
import com.app.resource_service.dto.HospitalCreationDto;
import com.app.resource_service.dto.MobileMedicalCampCreationDto;
import com.app.resource_service.dto.ReliefCampCreationDto;
import com.app.resource_service.repository.ContactRepository;
import com.app.resource_service.repository.HospitalRepository;
import com.app.resource_service.repository.MobileMedicalCampRepository;
import com.app.resource_service.repository.ReliefCampRepository;
import com.app.resource_service.resources.Contact;
import com.app.resource_service.resources.Hospital;
import com.app.resource_service.resources.MobileMedicalCamp;
import com.app.resource_service.resources.ReliefCamp;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MobileMedicalCampRepository mobileMedicalCampRepository;

    @Autowired
    private ReliefCampRepository reliefCampRepository;

    @Transactional
    public String onBoardHospital(HospitalCreationDto hospitalCreationDto){

//        todo: utils class for converting coordinates to Point object

        Hospital hospital = modelMapper.map(hospitalCreationDto,Hospital.class);

        hospital.setAvailableBeds(hospitalCreationDto.getTotalBeds());
        hospital.setLocation(GeometryUtils.createPoint(hospitalCreationDto.getLocation()));

        Contact contact = new Contact();
        contact.setEmail(hospitalCreationDto.getEmail());
        contact.setPhone(hospitalCreationDto.getPhone());

        Contact savedContact = contactRepository.save(contact);
        hospital.setContact(savedContact);

        hospitalRepository.save(hospital);


        return "hospital added successfully";



    }
    @Transactional
    public String onBoardMedicalCamp(MobileMedicalCampCreationDto mobileMedicalCampCreationDto){

//        todo: utils class for converting coordinates to Point object

        MobileMedicalCamp mobileMedicalCamp = modelMapper.map(mobileMedicalCampCreationDto,MobileMedicalCamp.class);

        mobileMedicalCamp.setHasMedicalSupplies(true);
        mobileMedicalCamp.setHasMedicines(true);
        mobileMedicalCamp.setHasFirstAid(true);

        mobileMedicalCamp.setLocation(GeometryUtils.createPoint(mobileMedicalCampCreationDto.getLocation()));


        Contact contact = new Contact();
        contact.setEmail(mobileMedicalCampCreationDto.getEmail());
        contact.setPhone(mobileMedicalCampCreationDto.getPhone());

        Contact savedContact = contactRepository.save(contact);
        mobileMedicalCamp.setContact(savedContact);

        mobileMedicalCampRepository.save(mobileMedicalCamp);


        return "medical camp added successfully added successfully";



    }

    @Transactional
    public String onBoardReliefCamp(ReliefCampCreationDto reliefCampCreationDto){

//        todo: utils class for converting coordinates to Point object

        ReliefCamp reliefCamp = modelMapper.map(reliefCampCreationDto,ReliefCamp.class);

        reliefCamp.setHasFoodSupply(true);
        reliefCamp.setCurrentOccupancy(reliefCampCreationDto.getTotalCapacity());
        reliefCamp.setHasMedicalAid(true);
        reliefCamp.setHasSanitation(true);
        reliefCamp.setHasWaterSupply(true);

        reliefCamp.setLocation(GeometryUtils.createPoint(reliefCampCreationDto.getLocation()));



        Contact contact = new Contact();
        contact.setEmail(reliefCampCreationDto.getEmail());
        contact.setPhone(reliefCampCreationDto.getPhone());

        Contact savedContact = contactRepository.save(contact);
        reliefCamp.setContact(savedContact);


        reliefCampRepository.save(reliefCamp);


        return "relief camp added successfully added successfully";



    }
    @Transactional
    public String deleteHospital(Long id){

        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hospital not found"));

        contactRepository.delete(hospital.getContact());
        hospitalRepository.delete(hospital);

        return "hospital deleted successfully";
    }

    @Transactional
    public String deleteReliefCamp(Long id){

        ReliefCamp reliefCamp = reliefCampRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("relief camp not found not found"));
        contactRepository.delete(reliefCamp.getContact());
        reliefCampRepository.delete(reliefCamp);

        return "relief canp deleted successfully";
    }

    @Transactional
    public String deleteMedicalCamo(Long id){

        MobileMedicalCamp mobileMedicalCamp = mobileMedicalCampRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("medical camp not found"));

        contactRepository.delete(mobileMedicalCamp.getContact());
        mobileMedicalCampRepository.delete(mobileMedicalCamp);

        return "medical camp deleted successfully";
    }

}
