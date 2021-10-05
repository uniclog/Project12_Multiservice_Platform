package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods;

import lombok.Data;
import org.springframework.web.client.RestTemplate;

@Data
public abstract class WebUtilsHttpMethodAbstract implements WebUtilsHttpMethodInterface {
    private RestTemplate restTemplate = new RestTemplate();
}
