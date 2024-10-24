
### 1. Sentiment Analysis
    task="text-classification"
    Model = distilbert/distilbert-base-uncased-finetuned-sst-2-english
    URL = https://huggingface.co/distilbert/distilbert-base-uncased-finetuned-sst-2-english

### 2. Financial sentiment analysis
    task="text-classification"
    Model = ProsusAI/finbert
    URL = https://huggingface.co/ProsusAI/finbert

### 3. Named Entity Recognition
    task = ner
    Model = dbmdz/bert-large-cased-finetuned-conll03-english
    URL = https://huggingface.co/dbmdz/bert-large-cased-finetuned-conll03-english

### 4. Question Answering
    task = question-answering
    Model = distilbert/distilbert-base-cased-distilled-squad
    URL = https://huggingface.co/distilbert/distilbert-base-cased-distilled-squad

### 5. Translation (English To French)
  task = translation_en_to_fr
  Model = google-t5/t5-base
  URL: https://huggingface.co/google-t5/t5-base

### 6. Text to Text Generation
  Model = GPT-2
  URL = https://huggingface.co/openai-community/gpt2

  Other Text2Text Models
  1. https://huggingface.co/models?pipeline_tag=text2text-generation&sort=trending