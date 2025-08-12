package com.example.lambda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathRequest;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathResponse;
import software.amazon.awssdk.services.ssm.model.Parameter;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParameterStoreService {
  private static final Logger log = LoggerFactory.getLogger(ParameterStoreService.class);

  private final SsmClient ssm;
  private final long ttlMillis;
  private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

  public ParameterStoreService(Region region, Duration ttl) {
    this.ssm = SsmClient.builder().region(region).build();
    this.ttlMillis = ttl.toMillis();
  }

  public String getParameter(String name, boolean decrypt) {
    CacheEntry ce = cache.get(name);
    if (ce != null && !ce.isExpired()) return ce.value;

    GetParameterResponse resp = ssm.getParameter(GetParameterRequest.builder()
        .name(name).withDecryption(decrypt).build());
    String val = resp.parameter().value();
    cache.put(name, CacheEntry.of(val, ttlMillis));
    return val;
  }

  public Map<String, String> getParametersByPath(String path, boolean recursive, boolean decrypt) {
    Map<String, String> out = new LinkedHashMap<>();
    String token = null;
    do {
      GetParametersByPathResponse resp = ssm.getParametersByPath(GetParametersByPathRequest.builder()
          .path(path)
          .recursive(recursive)
          .withDecryption(decrypt)
          .nextToken(token)
          .build());
      for (Parameter p : resp.parameters()) {
        out.put(p.name(), p.value());
        cache.put(p.name(), CacheEntry.of(p.value(), ttlMillis));
      }
      token = resp.nextToken();
    } while (token != null && !token.isEmpty());
    return out;
  }

  private record CacheEntry(String value, long expiresAt) {
    static CacheEntry of(String value, long ttlMillis) {
      return new CacheEntry(value, System.currentTimeMillis() + ttlMillis);
    }
    boolean isExpired() { return System.currentTimeMillis() > expiresAt; }
  }
}
