
1. [AWS ML Fridays - Fine Tuning Large Language Models By Sudhanshu Hate](https://in-resources.awscloud.com/india-on-demand-webinars/aws-ml-fridays-fine-tuning-large-language-models-4)

## Different Types of Fine Tuning

# Model Learning Stages

## 1. [Pre-training](#)
The initial phase where a model learns from large-scale, general-purpose data using unsupervised or self-supervised learning. This builds the model's foundational knowledge.

## 2. [Prompt Engineering – In-Context Learning](#)
A technique where the model is given task instructions and examples directly in the prompt without any parameter updates.

- **Zero-shot**: The model performs a task without seeing any examples.
- **N-shots**: The model is given a few examples (e.g., 1-shot, 3-shot) to understand the task.

## 3. [Advanced Prompting Techniques](#)

- **Chain of Thought (COT) prompting**: Encourages the model to reason step-by-step for complex tasks.
- **Self-Consistency**: Samples multiple reasoning paths and chooses the most consistent answer.
- **General Knowledge**: Leverages broad knowledge encoded during pretraining.
- **ReAct (Reasoning and Acting)**: Combines reasoning with actions, such as querying external tools or APIs during inference.

## 4. [Retrieval Augmented Generation (RAG)](#)
Enhances generation by retrieving relevant documents from a knowledge base and using them as context, improving factual accuracy and knowledge recall.

## 5. [Model Fine Tuning](#)
Customizes the model by updating its weights using domain-specific data. Improves performance for targeted applications (e.g., medical summarization, legal Q&A).
