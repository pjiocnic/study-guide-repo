package cookbook.oracle.fillb.web;

import cookbook.oracle.fillb.service.FillBService;
import cookbook.oracle.fillb.service.FillBService.ResultRow;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
  private final FillBService service;
  public BatchController(FillBService service) { this.service = service; }

  @PostMapping("/run")
  public List<ResultRow> run() { return service.processBatch(); }
}
