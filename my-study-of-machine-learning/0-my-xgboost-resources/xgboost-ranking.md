[A Real-Time Recommendation Engine with SageMaker Feature Store](https://github.com/aws-samples/sagemaker-feature-store-real-time-recommendations)
  * It implements a **real-time recommendation engine** for an e-commerce website using Amazon SageMaker Feature Store (online + offline modes).
  * It uses a **hybrid approach**:
    1. A **Collaborative Filtering** model (matrix factorization / factorization machines) to fetch initial candidate products based on historical data.
    2. A **Ranking model** (XGBoost) that scores those candidate products using features from streaming click data (aggregated in real time) + product data, to pick the top ones.
  * The streaming click data is ingested via **Kinesis Data Streams**.
  * Aggregation over short time windows (e.g. last 2 minutes) is done via **Kinesis Data Analytics / sliding windows**.
  * Aggregated features get stored in an **online Feature Store feature group** for low-latency retrieval during inference. 
