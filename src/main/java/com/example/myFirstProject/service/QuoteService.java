package com.example.myFirstProject.service;

import com.example.myFirstProject.api.response.WheatherApiResponse;
import com.example.myFirstProject.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    @Autowired
    private AppCache appCache;

    @Value("${wheather.api.key}")
    private String apiKey;

    private final String API = appCache.APP_CACHE.get("wheather_api");

    @Autowired
    private RestTemplate restTemplate;

    public WheatherApiResponse getWeather(String city) {
        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);

        ResponseEntity<WheatherApiResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WheatherApiResponse.class);

        WheatherApiResponse body = response.getBody();
        return body;
    }
}
