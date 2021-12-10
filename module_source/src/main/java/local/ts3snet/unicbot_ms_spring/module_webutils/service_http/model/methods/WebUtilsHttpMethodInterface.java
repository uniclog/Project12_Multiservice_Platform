package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.HttpOptions;
import org.springframework.http.ResponseEntity;

public interface WebUtilsHttpMethodInterface {
    ResponseEntity<String> execute(HttpOptions options);
}
