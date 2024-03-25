package hei.school.soratra.endpoint.rest.controller.soratra.dto;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Soratra {
  private URL original_url;
  private URL transformed_url;
}
