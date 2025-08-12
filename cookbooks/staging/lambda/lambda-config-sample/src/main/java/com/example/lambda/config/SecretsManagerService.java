package com.example.lambda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecretsManagerService {
  private static final Logger log = LoggerFactory.getLogger(SecretsManagerService.class);

  private final SecretsManagerClient sm;
  private final long ttlMillis;
  private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

  public SecretsManagerService(Region region, Duration ttl) {
    this.sm = SecretsManagerClient.builder().region(region).build();
    this.ttlMillis = ttl.toMillis();
  }

  public String getSecretString(String secretId) {
    CacheEntry ce = cache.get(secretId);
    if (ce != null && !ce.isExpired()) return ce.value();

    GetSecretValueResponse resp = sm.getSecretValue(GetSecretValueRequest.builder()
        .secretId(secretId).build());
    String val = resp.secretString();
    cache.put(secretId, CacheEntry.of(val, ttlMillis));
    return val;
  }

  public Map<String, String> getSecretJson(String secretId) {
    String json = getSecretString(secretId);
    return JsonMini.parseFlatObject(json);
  }

  private record CacheEntry(String value, long expiresAt) {
    static CacheEntry of(String value, long ttlMillis) {
      return new CacheEntry(value, System.currentTimeMillis() + ttlMillis);
    }
    boolean isExpired() { return System.currentTimeMillis() > expiresAt; }
  }
}
