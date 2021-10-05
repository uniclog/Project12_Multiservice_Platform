package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.service;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.HttpOptions;

public interface WebUtilsHttpExecutorService {
    void sentRequestHttpMethod(HttpOptions options);
}
