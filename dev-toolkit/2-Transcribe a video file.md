Your script is a **great starting point**, and yes — you can adapt it to split and transcribe a **`.mov` video file** as long as you extract the audio correctly. Let’s go over the key points:

---

## ✅ What Works in Your Script:

1. **Splits audio into 60-second chunks** using `pydub`.
2. **Transcribes each chunk with Whisper.**
3. **Combines the transcriptions into one file.**

---

## 🎥 What Needs to Change for a `.mov` Video:

`.mov` is a **video format**, so `pydub` cannot read it directly.
You must **extract the audio track first**. You can do this in one of two ways:

### 1️⃣ **Use `ffmpeg` (recommended)**:

You can extract the audio with `ffmpeg`:

```bash
ffmpeg -i input.mov -vn -acodec mp3 output.mp3
```

Then run your script on `output.mp3`.

---

### 2️⃣ **Use `moviepy` inside Python**:

You can also do this entirely in Python:

```python
from moviepy.editor import VideoFileClip

# Extract audio from the .mov file
video = VideoFileClip("input.mov")
video.audio.write_audiofile("audio.mp3")

# Now process audio.mp3 with pydub and Whisper as in your script
```

---

## ✏️ Updated Example:

Here’s a **complete example** using `moviepy` for video → audio conversion:

```python
import os
from pydub import AudioSegment
from moviepy.editor import VideoFileClip
import whisper

# Step 1: Extract audio from video file
video = VideoFileClip("input.mov")
video.audio.write_audiofile("audio.mp3")

# Step 2: Load the extracted audio
audio = AudioSegment.from_file("audio.mp3")

# Step 3: Split into chunks of 60 seconds
chunk_length_ms = 60 * 1000
chunks = [audio[i:i + chunk_length_ms] for i in range(0, len(audio), chunk_length_ms)]

# Step 4: Load Whisper model
model = whisper.load_model("small")

# Step 5: Transcribe chunks
os.makedirs("chunks_temp", exist_ok=True)
full_transcript = ""

for idx, chunk in enumerate(chunks):
    chunk_file = f"chunks_temp/chunk_{idx}.mp3"
    chunk.export(chunk_file, format="mp3")
    print(f"Transcribing chunk {idx + 1}/{len(chunks)}...")
    result = model.transcribe(chunk_file)
    full_transcript += result["text"] + " "

# Step 6: Save full transcript
with open("transcript.txt", "w", encoding="utf-8") as f:
    f.write(full_transcript.strip())

print("✅ Transcription complete! Check transcript.txt for the result.")
```

---

## 🎯 Summary:

✅ **Your chunking and transcription logic is fine.**
✅ **You just need to extract audio first** from the `.mov` file using `moviepy` or `ffmpeg`.
✅ Everything else stays the same.

Would you also like:

* A version using `ffmpeg` directly?
* Progress updates with `tqdm`?
* Support for Whisper’s `large` model or GPU?

Let me know — I can help with those too! 🎯
