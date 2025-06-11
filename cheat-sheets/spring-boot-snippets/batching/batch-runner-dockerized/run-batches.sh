#!/bin/bash

docker build -t batch-runner .

docker run -d --name batch1 batch-runner \
  --batch.start=0 --batch.end=99 --spring.profiles.active=dev

docker run -d --name batch2 batch-runner \
  --batch.start=100 --batch.end=199 --spring.profiles.active=prod

docker run -d --name batch3 batch-runner \
  --batch.start=200 --batch.end=299 --spring.profiles.active=test-batch
