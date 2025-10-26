package com.blogger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blogger.entity.ExternalUserEntity;

@Service
public class ExternalUserService {

    private static final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/users";

    // @Autowired
    private final RestTemplate restTemplate;

    public ExternalUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalUserEntity[] fetchExternalUserData() {
        ResponseEntity<ExternalUserEntity[]> response = restTemplate.exchange(EXTERNAL_API_URL, HttpMethod.GET, null,
                ExternalUserEntity[].class);
        return response.getBody();
    }

    public ExternalUserEntity createExternalUser(ExternalUserEntity externalUser) {

        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        //generating request body
        HttpEntity<ExternalUserEntity> requestEntity = new HttpEntity<>(externalUser, headers);
        ResponseEntity<ExternalUserEntity> response = restTemplate.exchange(EXTERNAL_API_URL, HttpMethod.POST,
                requestEntity, ExternalUserEntity.class);
        return response.getBody();
    }
}
