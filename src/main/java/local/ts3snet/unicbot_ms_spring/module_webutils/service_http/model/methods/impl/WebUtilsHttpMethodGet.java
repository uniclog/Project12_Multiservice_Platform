package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.HttpOptions;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods.WebUtilsHttpMethodAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component("webUtilsHttpMethodGet")
public class WebUtilsHttpMethodGet extends WebUtilsHttpMethodAbstract {
    @Override
    public void execute(HttpOptions options) {
        final HttpHeaders headers = new HttpHeaders();
        options.getHeaders().forEach(headers::set);
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        getRestTemplate().exchange(options.getUrl(), HttpMethod.GET, entity, String.class);
        // ResponseEntity<String> response =
        // response.getBody()
        log.debug("-------------------- action http GET: " + options);
    }
}