package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.HttpOptions;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods.WebUtilsHttpMethodAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

@Slf4j
@Lazy
@Component("webUtilsHttpMethodGet")
public class WebUtilsHttpMethodGet extends WebUtilsHttpMethodAbstract {
    @Override
    public ResponseEntity<String> execute(HttpOptions options) {

        final StringBuilder url = new StringBuilder();
        url.append(options.getUrl());
        options.getParameters().forEach((key, value) -> {
            url.append((url.toString().contains("?")) ? '&' : '?');
            url.append(key).append("=").append(value);
        });
        try {
            log.info(url.toString());
            ResponseEntity<String> response = getRestTemplate().exchange(url.toString(), HttpMethod.GET, options.getHeaders(), String.class);
            log.debug("-------------------- action http GET: " + options);
            return response;
        } catch (ResourceAccessException e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }
}