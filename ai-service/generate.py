from diffusers import StableDiffusionPipeline
import torch
import os

def generate_image(prompt):
    # Model load karna
    pipe = StableDiffusionPipeline.from_pretrained(
        "runwayml/stable-diffusion-v1-5",
        torch_dtype=torch.float16 if torch.cuda.is_available() else torch.float32
    )
    
    # Mac Air (Apple Silicon) ke liye speed optimization
    if torch.backends.mps.is_available():
        pipe = pipe.to("mps")
    elif torch.cuda.is_available():
        pipe = pipe.to("cuda")

    # Image generate karna
    print(f"Generating image for prompt: '{prompt}'...")
    image = pipe(prompt).images[0]
    
    # Static folder banana agar nahi hai toh
    os.makedirs("static", exist_ok=True)
    
    # Image ko save karna aur uska path return karna
    filepath = "static/output.png"
    image.save(filepath)
    
    return filepath