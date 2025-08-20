package cookbook.oracle.fillb.run;

import cookbook.oracle.fillb.service.FillBService;
import cookbook.oracle.fillb.service.FillBService.ResultRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchRunner implements CommandLineRunner {
  private static final Logger log = LoggerFactory.getLogger(BatchRunner.class);
  private final FillBService service;
  public BatchRunner(FillBService service) { this.service = service; }

  @Override
  public void run(String... args) {
    List<ResultRow> results = service.processBatch();
    long updated = results.stream().filter(r -> "UPDATED".equals(r.status)).count();
    long noId    = results.stream().filter(r -> "NO_ID_FOUND".equals(r.status)).count();
    long errors  = results.stream().filter(r -> "ERROR".equals(r.status)).count();
    log.info("Batch complete. updated={}, noIdFound={}, errors={}", updated, noId, errors);
  }
}
