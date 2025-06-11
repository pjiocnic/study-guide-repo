
## ✅ What can I Fine-Tune in Bedrock?

| Model Provider | Fine-Tuning Support | Notes |
|----------------|---------------------|-------|
| **Amazon Titan** | ✅ Yes              | Currently supports fine-tuning via the AWS Console or SDK |
| **Meta (LLaMA-2)** | ❌ Not yet         | Only inference supported |
| **Anthropic Claude** | ❌ No            | Instruction-following is built-in |
| **Cohere, AI21** | ❌ No               | Prompt-based customization only |
| **Mistral**     | ❌ Not yet           | Recently added (as of March 2024) — inference only |

> 🔐 **TL;DR**: Fine-tuning is only available for **Titan Text models** (as of now). For other models, use **prompt engineering** or **RAG**.

---

1. my-ml-notes/7-rag/Do the weights change during RAG training.md

---

### 🧩 **Hybrid Strategy: Instruction-Tuning + RAG**

This combo gives you **structured behavior with dynamic knowledge access**. Here's when it's ideal:

| Use Case | Why Use Hybrid |
|----------|----------------|
| **Domain-specific questions over external docs** | Instruction-tune the LLM to understand domain phrasing & tone, then plug into RAG for dynamic answers |
| **Consistent formats over variable contexts** | Fine-tune to enforce output style (e.g., summaries, structured JSON) while RAG fetches updated knowledge |
| **Limited data but high variability in queries** | Instruction-tune to generalize behavior; RAG covers the knowledge gaps |
| **Agent or multi-tool pipelines** | Instruction-tuned LLM acts as orchestrator; RAG handles factual grounding |