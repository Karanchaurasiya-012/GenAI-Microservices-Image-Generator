package com.example.backend.dto;

public class ImageRequest {
    private String prompt;

    // Default constructor
    public ImageRequest() {
    }

    // Parameterized constructor
    public ImageRequest(String prompt) {
        this.prompt = prompt;
    }

    // Getter and Setter
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}