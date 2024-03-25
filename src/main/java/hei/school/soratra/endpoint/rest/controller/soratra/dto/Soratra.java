package hei.school.soratra.endpoint.rest.controller.soratra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@AllArgsConstructor
@Getter
@Setter
public class Soratra {
    private URL original_url;
    private URL transformed_url;
}
