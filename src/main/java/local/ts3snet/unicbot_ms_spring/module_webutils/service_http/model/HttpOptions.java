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
@Scope(value = "prototype")
public class HttpOptions {
    private String url;
    private HttpMethod method = HttpMethod.GET;
    private String dataCharset = "UTF-8";
    private Map<String, String> headers = null;
    private Map<String, String> parameters = null;
    private String data;

    /**
     * Add header record
     * @param key header name
     * @param value header value
     */
    public void addHeader(String key, String value) {
        if (headers == null) headers = new HashMap<>();
        headers.put(key, value);
    }

    /**
     * Add new parameter
     * @param key parameter name
     * @param value parameter value
     */
    public void addParameters(String key, String value) {
        if (parameters == null) parameters = new HashMap<>();
        parameters.put(key, value);
    }
}
