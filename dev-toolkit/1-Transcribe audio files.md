A **complete Python script** you can run locally on your computer to transcribe the entire file using OpenAI’s Whisper. It will split the file into chunks automatically so you can process long audios without timeouts.

---

### 🐍 Python Script (transcribe.py)

```python
import os
from pydub import AudioSegment
import whisper

# Load your audio file
audio_path = "campusx-regression-2.m4a"  # Update with your file path
audio = AudioSegment.from_file(audio_path)

# Split into chunks of 60 seconds
chunk_length_ms = 60 * 1000
chunks = [audio[i:i + chunk_length_ms] for i in range(0, len(audio), chunk_length_ms)]

# Load Whisper model
model = whisper.load_model("small")

# Transcribe chunks
full_transcript = ""

# Create a temp directory to store chunks
os.makedirs("chunks_temp", exist_ok=True)

for idx, chunk in enumerate(chunks):
    chunk_file = f"chunks_temp/chunk_{idx}.mp3"
    chunk.export(chunk_file, format="mp3")
    print(f"Transcribing chunk {idx + 1}/{len(chunks)}...")
    result = model.transcribe(chunk_file)
    full_transcript += result["text"] + " "

# Save the full transcript
with open("transcript.txt", "w", encoding="utf-8") as f:
    f.write(full_transcript.strip())

print("✅ Transcription complete! Check transcript.txt for the result.")
```

---

### 📜 Instructions:

1. Make sure you have Python 3.9+ installed.

2. Install required libraries:

   ```bash
   pip install pydub openai-whisper
   ```

   *(If you don’t have FFmpeg installed, also install it — [download FFmpeg here](https://ffmpeg.org/) and add it to your PATH.)*

3. Save the script as `transcribe.py` and put it in the same folder as your `.m4a` file.

4. Run:

   ```bash
   python transcribe.py
   ```

5. Check `transcript.txt` for your transcription.

---

💡 Let me know if you’d also like help setting up the environment or customizing this further!
