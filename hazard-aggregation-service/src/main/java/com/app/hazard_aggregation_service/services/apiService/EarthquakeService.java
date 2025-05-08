package com.app.hazard_aggregation_service.services.apiService;

import com.app.hazard_aggregation_service.apiResponse.EarthquakeResponse;
import com.app.hazard_aggregation_service.apiResponse.earthquake.EarthquakeFeature;
import com.app.hazard_aggregation_service.configs.GeometryUtil;
import com.app.hazard_aggregation_service.dto.EarthquakeDto;
import com.app.hazard_aggregation_service.entities.EarthquakeRecord;
import com.app.hazard_aggregation_service.exceptions.EarthquakeDataFetchException;
import com.app.hazard_aggregation_service.repositories.EarthquakeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EarthquakeService {

    @Value("${usgs.api.key}")
    private String USGS_API_KEY;

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    public void fetchData(){
        EarthquakeResponse response = restTemplate.getForObject(USGS_API_KEY, EarthquakeResponse.class);

        if(response == null || response.getFeatures()==null){
            log.error("error in fetching earthquake data from api");
            throw new EarthquakeDataFetchException("unable to fetch earthquake data");
        }

        for(EarthquakeFeature feature : response.getFeatures()){
            double magnitude = feature.getProperties().getMag();

//            todo: save data to db on basis of magnitude
//            mag >
            if (magnitude >4.0){
                EarthquakeRecord earthquakeRecord = new EarthquakeRecord();
                earthquakeRecord.setAlerted(false);
                earthquakeRecord.setDepth(feature.getGeometry().getCoordinates().getLast());
                earthquakeRecord.setEventId(feature.getId());
                earthquakeRecord.setMagnitude(magnitude);
                earthquakeRecord.setRegion(feature.getProperties().getPlace());
                earthquakeRecord.setUrl(feature.getProperties().getUrl());
                earthquakeRecord.setTimestamp(feature.getProperties().getTime());
                earthquakeRecord.setMagnitude(feature.getProperties().getMag());
                earthquakeRecord.setLocation(GeometryUtil.getPoint(feature.getGeometry().getCoordinates().get(0),feature.getGeometry().getCoordinates().get(1)));

                if (magnitude <6.0){
                    earthquakeRecord.setSeverityLevel("medium");
                }
                else{
                    earthquakeRecord.setSeverityLevel("high");
                }
                earthquakeRepository.save(earthquakeRecord);

            }
        }
    }

    public List<EarthquakeDto> getEarthquakeData(){

        List<EarthquakeRecord> earthquakeRecords = earthquakeRepository.findAll();

        List<EarthquakeDto> earthquakes = new ArrayList<>();

        for(EarthquakeRecord earthquakeRecord : earthquakeRecords){

            EarthquakeDto earthquakeDto = new EarthquakeDto();
            earthquakeDto.setDepth(earthquakeRecord.getDepth());
            earthquakeDto.setLatitude(earthquakeRecord.getLocation().getY());
            earthquakeDto.setLongitude(earthquakeRecord.getLocation().getX());
            earthquakeDto.setMagnitude(earthquakeRecord.getMagnitude());
            earthquakeDto.setRegion(earthquakeRecord.getRegion());
            earthquakeDto.setSeverityLevel(earthquakeRecord.getSeverityLevel());
            earthquakeDto.setTimestamp(earthquakeDto.getTimestamp());

            earthquakes.add(earthquakeDto);
        }

        return  earthquakes;

    }


}
