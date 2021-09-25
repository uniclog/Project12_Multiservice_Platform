package local.ts3snet.unicbot_ms_spring.module_webutils.service_http.service.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.HttpOptions;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.methods.WebUtilsHttpMethodInterface;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.service.WebUtilsHttpExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebUtilsHttpExecutorServiceImpl implements WebUtilsHttpExecutorService {
    @Lazy
    @Autowired
    @Qualifier("webUtilsHttpMethodGet")
    private WebUtilsHttpMethodInterface methodGet;

    @Lazy
    @Autowired
    @Qualifier("webUtilsHttpMethodPost")
    private WebUtilsHttpMethodInterface methodPost;

    @Lazy
    @Autowired
    @Qualifier("webUtilsHttpMethodDelete")
    private WebUtilsHttpMethodInterface methodDelete;

    @Lazy
    @Autowired
    @Qualifier("webUtilsHttpMethodPut")
    private WebUtilsHttpMethodInterface methodPut;

    @Override
    public void sentRequestHttpMethod(HttpOptions options) {

        switch (options.getMethod()) {
            case GET: {
                methodGet.execute(options);
                break;
            }
            case POST: {
                methodPost.execute(options);
                break;
            }
            case DELETE: {
                methodDelete.execute(options);
                break;
            }
            case PUT: {
                methodPut.execute(options);
                break;
            }
        }
    }
}
