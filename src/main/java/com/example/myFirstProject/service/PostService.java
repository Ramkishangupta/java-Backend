package com.example.myFirstProject.service;

import com.example.myFirstProject.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {
    private static final String API_KEY_POST = "http://localhost:8081/public";

    @Autowired
    private RestTemplate restTemplate;

    public void postMethod(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        User user = User.builder().userName("ram1").password("ram1").build();
        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);
        restTemplate.exchange(API_KEY_POST, HttpMethod.POST,httpEntity,String.class);
    }
}
