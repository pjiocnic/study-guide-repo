package com.example.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.time.Duration;

@Configuration
public class SqsConfig {

  @Bean
  public SqsAsyncClient sqsAsyncClient(@Value("${aws.region}") String region) {
    return SqsAsyncClient.builder()
        .region(Region.of(region))
        .httpClientBuilder(NettyNioAsyncHttpClient.builder()
            .maxConcurrency(256)
            .readTimeout(Duration.ofSeconds(30))
            .writeTimeout(Duration.ofSeconds(30)))
        .build();
  }
}
