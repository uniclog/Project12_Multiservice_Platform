package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.HttpOptions;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods.WebUtilsHttpMethodInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component("webUtilsHttpMethodDelete")
public class WebUtilsHttpMethodDelete implements WebUtilsHttpMethodInterface {
    @Override
    public void execute(HttpOptions options) {
        log.info("-------------------- action http DELETE");
    }
}