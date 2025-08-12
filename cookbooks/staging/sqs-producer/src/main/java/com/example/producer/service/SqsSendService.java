package com.example.producer.service;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class SqsSendService {
  private static final Logger log = LoggerFactory.getLogger(SqsSendService.class);

  private final SqsAsyncClient sqs;
  private final String queueUrl;
  private final boolean fifo;
  private final String messageGroupId;
  private final String dedupStrategy;

  public SqsSendService(
      SqsAsyncClient sqs,
      @Value("${aws.queueUrl}") String queueUrl,
      @Value("${producer.fifo:false}") boolean fifo,
      @Value("${producer.messageGroupId:group-1}") String messageGroupId,
      @Value("${producer.dedupStrategy:none}") String dedupStrategy) {
    this.sqs = sqs; this.queueUrl = queueUrl; this.fifo = fifo;
    this.messageGroupId = messageGroupId; this.dedupStrategy = dedupStrategy;
  }

  public void sendBatch(List<String> lines) throws Exception {
    if (lines.isEmpty()) return;

    List<SendMessageBatchRequestEntry> entries = new ArrayList<>(lines.size());
    int i = 0;
    for (String body : lines) {
      SendMessageBatchRequestEntry.Builder b = SendMessageBatchRequestEntry.builder()
          .id("m" + i++)
          .messageBody(body);

      if (fifo) {
        b = b.messageGroupId(messageGroupId)
             .messageDeduplicationId(dedupIdFor(body));
      }

      entries.add(b.build());
    }

    SendMessageBatchRequest req = SendMessageBatchRequest.builder()
        .queueUrl(queueUrl)
        .entries(entries)
        .build();

    for (int attempt = 1; attempt <= 5; attempt++) {
      CompletableFuture<SendMessageBatchResponse> f = sqs.sendMessageBatch(req);
      SendMessageBatchResponse resp = f.join();

      if (resp.failed().isEmpty()) return;

      Map<String, SendMessageBatchRequestEntry> byId = new HashMap<>();
      for (SendMessageBatchRequestEntry e : entries) byId.put(e.id(), e);

      List<SendMessageBatchRequestEntry> retry = new ArrayList<>();
      for (BatchResultErrorEntry err : resp.failed()) {
        log.warn("SQS failed id={} code={} msg={}", err.id(), err.code(), err.message());
        retry.add(byId.get(err.id()));
      }
      entries = retry;

      if (attempt < 5) {
        Thread.sleep((long) Math.min(5000, Math.pow(2, attempt) * 200));
        req = req.toBuilder().entries(entries).build();
      } else {
        throw new RuntimeException("SQS batch failed after retries, left=" + entries.size());
      }
    }
  }

  private String dedupIdFor(String body) throws Exception {
    return switch (dedupStrategy) {
      case "line" -> body;
      case "hash" -> sha256(body);
      default -> java.util.UUID.randomUUID().toString();
    };
  }

  private static String sha256(String s) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] bytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) sb.append(String.format("%02x", b));
    return sb.toString();
  }
}
