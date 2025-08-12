package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.lambda.config.ParameterStoreService;
import com.example.lambda.config.SecretsManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;

import java.time.Duration;
import java.util.Map;

public class Handler implements RequestHandler<Map<String,Object>, Map<String,String>> {
  private static final Logger log = LoggerFactory.getLogger(Handler.class);

  private static final Region REGION = Region.of(System.getenv().getOrDefault("AWS_REGION", "us-east-1"));
  private static final ParameterStoreService PARAMS = new ParameterStoreService(REGION, Duration.ofMinutes(5));
  private static final SecretsManagerService SECRETS = new SecretsManagerService(REGION, Duration.ofMinutes(5));

  private static final String PARAM_DB_URL = getenvOr("PARAM_DB_URL", "/myapp/prod/db/url");
  private static final String SECRET_DB   = getenvOr("SECRET_DB", "myapp/prod/dbCredentials");

  @Override
  public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
    String dbUrl = PARAMS.getParameter(PARAM_DB_URL, true);
    Map<String, String> creds = SECRETS.getSecretJsonMap(SECRET_DB);

    log.info("Fetched config: urlLen={}, user={}", dbUrl.length(), creds.get("username"));
    return Map.of(
        "dbUrl", dbUrl,
        "dbUser", creds.getOrDefault("username", "unknown"),
        "dbPassLen", String.valueOf(creds.getOrDefault("password", "").length())
    );
  }

  private static String getenvOr(String k, String def) {
    String v = System.getenv(k);
    return (v == null || v.isBlank()) ? def : v;
  }
}
