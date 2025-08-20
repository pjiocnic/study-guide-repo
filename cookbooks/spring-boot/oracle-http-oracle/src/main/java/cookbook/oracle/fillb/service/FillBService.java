package cookbook.oracle.fillb.service;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FillBService {
  private static final Logger log = LoggerFactory.getLogger(FillBService.class);

  private final JdbcTemplate jdbc;
  private final RestTemplate http;

  @Value("${app.batch-size:100}")
  private int batchSize;

  @Value("${app.remote.url}")
  private String urlTemplate;

  @Value("${app.remote.jsonPath:$.id}")
  private String idJsonPath;

  @Value("${app.use-skip-locked:true}")
  private boolean useSkipLocked;

  public FillBService(JdbcTemplate jdbc, RestTemplate http) {
    this.jdbc = jdbc;
    this.http = http;
  }

  /** Processes one batch in a single transaction. */
  @Transactional
  public List<ResultRow> processBatch() {
    List<String> ids = selectIdsNeedingB(batchSize);
    List<ResultRow> results = new ArrayList<>();

    for (String a : ids) {
      try {
        String u = urlTemplate.replace("{id}", a);
        String body = fetch(u);
        String extracted = extractId(body, idJsonPath);

        if (extracted != null && !extracted.trim().isEmpty()) {
          int updated = updateB(a, extracted.trim());
          if (updated == 1) {
            results.add(ResultRow.ok(a, extracted));
          } else {
            results.add(ResultRow.error(a, "Row not updated (concurrent change?)"));
          }
        } else {
          results.add(ResultRow.noId(a));
        }
      } catch (Exception e) {
        results.add(ResultRow.error(a, e.getMessage()));
      }
    }
    return results;
  }

  private List<String> selectIdsNeedingB(int limit) {
    if (useSkipLocked) {
      String sql = "SELECT A FROM A WHERE B IS NULL AND ROWNUM <= ? FOR UPDATE SKIP LOCKED";
      return jdbc.query(sql, ps -> ps.setInt(1, limit), (rs, i) -> rs.getString(1));
    } else {
      String sql = "SELECT A FROM A WHERE B IS NULL AND ROWNUM <= ? ORDER BY A";
      return jdbc.query(sql, ps -> ps.setInt(1, limit), (rs, i) -> rs.getString(1));
    }
  }

  private int updateB(String a, String b) {
    String sql = "UPDATE A SET B = ? WHERE A = ?";
    return jdbc.update(sql, ps -> {
      ps.setString(1, b);
      ps.setString(2, a);
    });
  }

  @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2.0))
  protected String fetch(String url) {
    ResponseEntity<String> resp = http.getForEntity(url, String.class);
    if (!resp.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException("HTTP " + resp.getStatusCodeValue());
    }
    return resp.getBody() == null ? "" : resp.getBody();
  }

  protected String extractId(String json, String jsonPath) {
    try {
      Object v = JsonPath.read(json, jsonPath);
      return v == null ? null : String.valueOf(v);
    } catch (Exception e) {
      return null;
    }
  }

  public static class ResultRow {
    public final String a;
    public final String b;
    public final String status;
    public final String error;
    private ResultRow(String a, String b, String status, String error) {
      this.a = a; this.b = b; this.status = status; this.error = error;
    }
    public static ResultRow ok(String a, String b) { return new ResultRow(a, b, "UPDATED", null); }
    public static ResultRow noId(String a)         { return new ResultRow(a, null, "NO_ID_FOUND", null); }
    public static ResultRow error(String a, String err){ return new ResultRow(a, null, "ERROR", err); }
  }
}
