from flask import Flask, request, send_file, jsonify
from generate import generate_image
import os

app = Flask(__name__)

# Yeh endpoint ab /api/generate ban gaya hai
@app.route('/api/generate', methods=['POST'])
def generate():
    # Spring Boot se JSON format mein prompt aayega
    data = request.get_json()
    
    # Agar prompt nahi bheja, toh error denge
    if not data or 'prompt' not in data:
        return jsonify({"error": "Prompt is required!"}), 400
    
    prompt = data['prompt']
    
    try:
        # generate.py se image banwa kar path nikalna
        image_path = generate_image(prompt)
        
        # Generated image ko wapas Spring Boot ko bhej dena
        return send_file(image_path, mimetype='image/png')
    
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    # Flask ko port 5000 par run karenge
    app.run(host='0.0.0.0', port=5001, debug=True)