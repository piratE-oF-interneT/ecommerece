package com.app.hazard_aggregation_service.services.apiService;


import com.app.hazard_aggregation_service.apiResponse.flood.CityForecast;
import com.app.hazard_aggregation_service.entities.Flood;
import com.app.hazard_aggregation_service.entities.FloodData;
import com.app.hazard_aggregation_service.entities.FloodProneCities;
import com.app.hazard_aggregation_service.repositories.FloodCityRepository;
import com.app.hazard_aggregation_service.repositories.FloodDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloodService {

    @Value("${flood.api.key}")
    private String API_KEY;

    @Autowired
    private FloodCityRepository floodCityRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FloodDataRepository floodDataRepository;





    public void fetchData(){

        List<FloodProneCities> cities = floodCityRepository.findAll();




        for(FloodProneCities city: cities){

            CityForecast cityForecast = getCityForeCast(city);

            System.out.println(cityForecast);

            if(cityForecast == null){
                continue;
            }

            List<FloodData> floodDataList = cityForecast.getForecast().stream()
                    .map(forecastData -> modelMapper.map(forecastData , FloodData.class))
                    .toList();

            floodDataList.forEach(floodData -> {
                                floodDataRepository.save(floodData);
                            });


            city.setFloodData(floodDataList);
        }



    }

    public CityForecast getCityForeCast(FloodProneCities city){
        String apiKey = API_KEY;
        String baseUrl = "https://api.tomorrow.io/v4/weather/forecast";

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("location", 40.7128 + "," + -74.0060)
                .queryParam("timesteps", "1d")

                .queryParam("apikey", API_KEY)
                .build()
                .toUri();
        return restTemplate.getForObject(uri, CityForecast.class);
    }



}
