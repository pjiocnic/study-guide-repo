package cookbook.oracle.fillb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

  @Bean
  public RestTemplate restTemplate() {
    SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
    f.setConnectTimeout(10_000);
    f.setReadTimeout(20_000);
    return new RestTemplate(f);
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource ds) {
    JdbcTemplate jt = new JdbcTemplate(ds);
    jt.setFetchSize(200);
    return jt;
  }
}
