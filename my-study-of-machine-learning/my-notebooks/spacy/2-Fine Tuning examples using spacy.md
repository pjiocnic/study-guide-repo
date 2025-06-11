
# Basic Example

Let's create a simple end-to-end example of fine-tuning a pre-trained model using **SpaCy**. We'll fine-tune the `en_core_web_sm` model to recognize a custom named entity—say, recognizing "Gadget" as a product.

This example assumes you have Python installed along with SpaCy.

---

### **Step 1: Install SpaCy**
First, ensure SpaCy is installed:
```bash
pip install spacy
```

---

### **Step 2: Load the Pre-trained Model**
Start with SpaCy’s `en_core_web_sm` model:
```python
import spacy

# Load the pre-trained SpaCy model
nlp = spacy.load("en_core_web_sm")
```

---

### **Step 3: Prepare Training Data**
Fine-tuning requires labeled data in SpaCy's format:
```python
# Training data: Each entry is (text, annotations)
TRAIN_DATA = [
    ("I just bought a new iPhone.", {"entities": [(17, 23, "GADGET")]}),
    ("Samsung released the Galaxy S23.", {"entities": [(20, 30, "GADGET")]}),
    ("The Pixel 7 is an amazing gadget.", {"entities": [(4, 11, "GADGET")]}),
    ("I love my Kindle Paperwhite!", {"entities": [(10, 27, "GADGET")]}),
]
```

Here:
- `entities` is a list of tuples with `(start, end, label)`, where `start` and `end` are character offsets.

---

### **Step 4: Add a New Entity Label**
Before training, register the new label (`GADGET`) with the model:
```python
# Add a new entity label to the NER component
ner = nlp.get_pipe("ner")
ner.add_label("GADGET")
```

---

### **Step 5: Fine-Tune the Model**
Fine-tune the model on the new data:
```python
from spacy.training.example import Example
from spacy.util import minibatch
import random

# Disable other pipelines during training for efficiency
pipe_exceptions = ["ner"]
unaffected_pipes = [pipe for pipe in nlp.pipe_names if pipe not in pipe_exceptions]

# Begin training
with nlp.disable_pipes(*unaffected_pipes):  # Disable other pipelines
    optimizer = nlp.resume_training()
    for epoch in range(20):  # Train for 20 epochs
        random.shuffle(TRAIN_DATA)
        losses = {}
        for batch in minibatch(TRAIN_DATA, size=2):  # Use small batches
            examples = []
            for text, annotations in batch:
                doc = nlp.make_doc(text)
                examples.append(Example.from_dict(doc, annotations))
            nlp.update(examples, drop=0.3, losses=losses)
        print(f"Epoch {epoch} - Losses: {losses}")
```

---

### **Step 6: Test the Fine-Tuned Model**

After training, test the model to see if it recognizes gadgets:
```python
# Test the model
test_text = "The new AirPods are amazing!"
doc = nlp(test_text)
print("Entities:", [(ent.text, ent.label_) for ent in doc.ents])
```

Expected output:
```
Entities: [('AirPods', 'GADGET')]
```

---

### **Step 7: Save the Fine-Tuned Model**
Save the model for future use:
```python
nlp.to_disk("fine_tuned_gadget_ner")
```

---

### **Step 8: Load the Fine-Tuned Model**
You can load the saved model anytime:
```python
import spacy

# Load the fine-tuned model
nlp = spacy.load("fine_tuned_gadget_ner")

# Test it
doc = nlp("I think the Surface Pro is a great device.")
print("Entities:", [(ent.text, ent.label_) for ent in doc.ents])
```

---

### Key Points About Fine-Tuning
1. **Pretrained Base:** Fine-tuning starts from a well-trained base model (e.g., `en_core_web_sm`) that already knows general language patterns.
2. **Custom Task:** New labels (like `GADGET`) are added based on your specific task.
3. **Incremental Learning:** The model learns the new task without forgetting the base task, as long as training data is well-balanced.

This example is simple and beginner-friendly while demonstrating how fine-tuning can adapt a pre-trained model for custom tasks.

---

# Fixed pattern recognition

To recognize phone numbers and documents (e.g., IDs, passports, or document names) using **SpaCy**, you can fine-tune its Named Entity Recognition (NER) pipeline. Here’s an end-to-end guide tailored to your case:

---

### **Step 1: Install SpaCy**
Ensure SpaCy is installed:
```bash
pip install spacy
```

---

### **Step 2: Load a Pretrained Model**
Load a SpaCy model to fine-tune:
```python
import spacy

# Load the base SpaCy model
nlp = spacy.load("en_core_web_sm")
```

---

### **Step 3: Prepare Training Data**
Your training data should contain text with labeled entities (`PHONE` for phone numbers, `DOCUMENT` for document names).

Here’s an example:
```python
TRAIN_DATA = [
    ("Call me at 123-456-7890.", {"entities": [(11, 23, "PHONE")]}),
    ("My phone number is +1 (234) 567-8901.", {"entities": [(18, 35, "PHONE")]}),
    ("I need a copy of my Passport.", {"entities": [(21, 29, "DOCUMENT")]}),
    ("The report ID is 1234-5678.", {"entities": [(16, 25, "DOCUMENT")]}),
    ("Contact 9876543210 for support.", {"entities": [(8, 18, "PHONE")]}),
]
```

In `entities`, `(start, end, label)` indicates the character offsets and the label.

---

### **Step 4: Add Labels to the NER Pipeline**
Register your custom labels:
```python
# Add new labels
ner = nlp.get_pipe("ner")
ner.add_label("PHONE")
ner.add_label("DOCUMENT")
```

---

### **Step 5: Fine-Tune the Model**
Train the model with your custom labels:
```python
from spacy.training.example import Example
from spacy.util import minibatch
import random

# Disable other components during training
pipe_exceptions = ["ner"]
unaffected_pipes = [pipe for pipe in nlp.pipe_names if pipe not in pipe_exceptions]

# Begin training
with nlp.disable_pipes(*unaffected_pipes):  # Disable other components
    optimizer = nlp.resume_training()
    for epoch in range(20):  # Train for 20 epochs
        random.shuffle(TRAIN_DATA)
        losses = {}
        for batch in minibatch(TRAIN_DATA, size=2):  # Use small batches
            examples = []
            for text, annotations in batch:
                doc = nlp.make_doc(text)
                examples.append(Example.from_dict(doc, annotations))
            nlp.update(examples, drop=0.3, losses=losses)
        print(f"Epoch {epoch} - Losses: {losses}")
```

---

### **Step 6: Test the Model**
Test the fine-tuned model on new sentences:
```python
# Test the model
test_text = "Call John at +44 7700 900123 or check Passport 9876."
doc = nlp(test_text)
print("Entities:", [(ent.text, ent.label_) for ent in doc.ents])
```

Expected Output:
```
Entities: [('+44 7700 900123', 'PHONE'), ('Passport', 'DOCUMENT')]
```

---

### **Step 7: Save and Load the Fine-Tuned Model**
Save your model:
```python
nlp.to_disk("phone_document_ner")
```

Load the model later:
```python
nlp = spacy.load("phone_document_ner")
doc = nlp("Send the file with ID 2023-0012 to +1 (800) 555-0199.")
print("Entities:", [(ent.text, ent.label_) for ent in doc.ents])
```

---

### **How to Handle Complex Patterns (e.g., Phone Numbers)**

If your entities (like phone numbers) follow specific patterns, you can combine SpaCy with **regular expressions** to preprocess text before fine-tuning. For instance:
```python
import re

# Regex to match phone numbers
phone_pattern = re.compile(r'\+?\d{1,4}[\s-]?\(?\d{1,4}\)?[\s-]?\d{1,4}[\s-]?\d{1,9}')
text = "Contact me at +1-800-555-0199."

# Match phone numbers
matches = phone_pattern.finditer(text)
for match in matches:
    print(f"Found phone number: {match.group()}")
```

You could also train the model on more diverse data, augmenting examples with these patterns.

---

### **Key Points**
1. **Training Data Quality**: Include diverse examples of phone numbers and documents.
2. **Custom Patterns**: Use regex for preprocessing or augmenting training data.
3. **Fine-Tuning Limitations**: If your data is small, overfitting is a risk. Regularization (`drop=0.3`) helps mitigate it.
4. **Combine Tools**: For highly structured patterns, use a hybrid of regex and NER for the best results.

This approach ensures your model recognizes phone numbers and document names in various formats and contexts.