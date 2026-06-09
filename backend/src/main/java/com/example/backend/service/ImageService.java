package com.example.backend.service;

import com.example.backend.dto.ImageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageService {

    // application.properties se Python URL utha rahe hain
    @Value("${python.ai.service.url}")
    private String pythonServiceUrl;

    public byte[] generateImageFromPython(String promptText) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ImageRequest requestBody = new ImageRequest(promptText);
        HttpEntity<ImageRequest> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<byte[]> response = restTemplate.postForEntity(
                pythonServiceUrl,
                requestEntity,
                byte[].class
        );

        return response.getBody();
    }
}