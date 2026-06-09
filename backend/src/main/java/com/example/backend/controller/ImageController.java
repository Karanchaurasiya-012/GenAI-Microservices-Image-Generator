package com.example.backend.controller;

import com.example.backend.dto.ImageRequest;
import com.example.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin(origins = "*") // Yeh React (port 3000) ko Spring Boot se baat karne dega
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateImage(@RequestBody ImageRequest request) {
        try {
            // Service ko prompt pass kiya
            byte[] imageBytes = imageService.generateImageFromPython(request.getPrompt());
            
            // Image bytes return kiye
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
            
        } catch (Exception e) {
            System.out.println("Error aagaya: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}