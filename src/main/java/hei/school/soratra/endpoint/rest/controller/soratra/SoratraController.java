package hei.school.soratra.endpoint.rest.controller.soratra;

import hei.school.soratra.endpoint.rest.controller.soratra.dto.Soratra;
import hei.school.soratra.service.soratra.SoratraService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/soratra")
@RequiredArgsConstructor
public class SoratraController {

  private final SoratraService service;

  @PutMapping("/{id}")
  public void putSoratra(@PathVariable String id, @RequestBody byte[] payload) throws IOException {
    service.saveSoratra(id, payload);
  }

  @GetMapping("/{id}")
  public Soratra getSoratra(@PathVariable String id) {
    return service.getSoratra(id).orElse(null);
  }
}
