package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Jsoup connection options
 */
@Data
@Component
@Scope("prototype")
public class HttpOptions {
    private String url;
    private HttpMethod method = HttpMethod.GET;
    private String dataCharset = "UTF-8";
    private Map<String, String> headers = new HashMap<>();
    private String data;

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }
}
