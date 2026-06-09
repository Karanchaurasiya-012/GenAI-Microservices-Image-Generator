#  Generative AI Image Synthesizer

![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![Spring Boot](https://img.shields.io/badge/spring_boot-%236DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
![Flask](https://img.shields.io/badge/flask-%23000.svg?style=for-the-badge&logo=flask&logoColor=white)

## About this project
This app is a simple demo of a text-to-image generator built with a few connected services:
- a React frontend,
- a Spring Boot backend, and
- a Python Flask service that calls Stable Diffusion.

It was created for the PBEL Equivalent to Virtual Internship - Generative AI by IBM. The goal was to build something practical that shows how different technologies can work together to generate images from user prompts.

## How it works
There are three main parts:

1. **Frontend (React, port 3000)**
   - lets you enter a prompt and shows the generated image.
2. **Backend (Spring Boot, port 8080)**
   - receives the request from the browser and sends it to the AI service.
3. **AI service (Python/Flask, port 5001)**
   - runs the `runwayml/stable-diffusion-v1-5` model and returns image data.

## What makes it useful
- Uses a modern Stable Diffusion workflow for text-to-image generation.
- Connects Java and Python services through simple REST APIs.
- Keeps the UI clean so you can focus on the prompt and the result.

## Run it locally

### Prerequisites
You should have these installed before starting:
- Node.js and npm
- Java 17 or newer
- Maven
- Python 3.9 or newer and pip

### Start the AI service
```bash
cd ai-service
pip install -r requirements.txt
python app.py
```

Once it is running, it should be available at `http://127.0.0.1:5001`.

### Start the backend
```bash
cd backend
./mvnw spring-boot:run
```

The backend should be available at `http://localhost:8080`.

### Start the frontend
```bash
cd frontend
npm install
npm start
```

Then open `http://localhost:3000` in your browser.

## How to use it
1. Open the app in your browser.
2. Type a prompt like `A futuristic flying car in a cyberpunk city`.
3. Click **Generate Image**.
4. Wait a few seconds for the result to appear.

## License
MIT License.

## Author
Karan Chaurasiya
