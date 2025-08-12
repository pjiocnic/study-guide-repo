package com.example.lambda.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecretsManagerService {
  private static final Logger log = LoggerFactory.getLogger(SecretsManagerService.class);

  private final SecretsManagerClient sm;
  private final long ttlMillis;
  private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
  private final ObjectMapper mapper = new ObjectMapper();

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

  /** Parse the secret JSON and return a flat Map of field -> string value. */
  public Map<String,String> getSecretJsonMap(String secretId) {
    try {
      String json = getSecretString(secretId);
      JsonNode node = mapper.readTree(json);
      Map<String,String> out = new LinkedHashMap<>();
      Iterator<Map.Entry<String, JsonNode>> it = node.fields();
      while (it.hasNext()) {
        Map.Entry<String, JsonNode> e = it.next();
        JsonNode v = e.getValue();
        out.put(e.getKey(), v.isTextual() ? v.asText() : v.toString());
      }
      return out;
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse secret JSON for id=" + secretId, e);
    }
  }

  /** Map the secret JSON to a strongly-typed POJO. */
  public <T> T getSecretAs(String secretId, Class<T> clazz) {
    try {
      String json = getSecretString(secretId);
      return mapper.readValue(json, clazz);
    } catch (Exception e) {
      throw new RuntimeException("Failed to bind secret to " + clazz.getSimpleName() + " for id=" + secretId, e);
    }
  }

  /** Get raw JsonNode if you want to traverse manually. */
  public JsonNode getSecretNode(String secretId) {
    try {
      String json = getSecretString(secretId);
      return mapper.readTree(json);
    } catch (Exception e) {
      throw new RuntimeException("Failed to read secret JSON tree for id=" + secretId, e);
    }
  }

  private record CacheEntry(String value, long expiresAt) {
    static CacheEntry of(String value, long ttlMillis) {
      return new CacheEntry(value, System.currentTimeMillis() + ttlMillis);
    }
    boolean isExpired() { return System.currentTimeMillis() > expiresAt; }
  }
}
