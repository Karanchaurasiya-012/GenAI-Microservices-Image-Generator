import React, { useState } from 'react';
import './App.css';

function App() {
  const [prompt, setPrompt] = useState("");
  const [imageUrl, setImageUrl] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const generateImage = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setImageUrl(null);

    try {
      // Spring Boot (Waiter) ko order dena
      const response = await fetch('http://localhost:8080/api/v1/images/generate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ prompt: prompt })
      });

      if (!response.ok) {
        throw new Error("Backend se error aaya hai bhai! Server check karo.");
      }

      // Spring Boot ne image bytes bhej diye hain, usko convert karna
      const imageBlob = await response.blob();
      const imageObjectURL = URL.createObjectURL(imageBlob);
      
      setImageUrl(imageObjectURL);
    } catch (err) {
      console.error("Error:", err);
      setError("Image generate nahi ho payi. Kya Spring Boot aur Python dono on hain?");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <h1>🧠 AI Image Generator</h1>
      <p>Powered by React + Spring Boot + Python</p>
      
      <form onSubmit={generateImage}>
        <input 
          type="text" 
          placeholder="Apna mast prompt yahan likho..." 
          value={prompt}
          onChange={(e) => setPrompt(e.target.value)}
          required 
        />
        <button type="submit" disabled={loading}>
          {loading ? 'Generating... (Mac Air kaam par hai)' : 'Generate Image'}
        </button>
      </form>

      {error && <p className="error">{error}</p>}

      {imageUrl && (
        <div className="image-container">
          <h3>🎉 Yeh rahi aapki Image!</h3>
          <img src={imageUrl} alt="Generated AI" />
        </div>
      )}
    </div>
  );
}

export default App;   