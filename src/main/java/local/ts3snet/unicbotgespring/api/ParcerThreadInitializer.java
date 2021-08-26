package local.ts3snet.unicbotgespring.api;

import local.ts3snet.unicbotgespring.service.UtilsWebServiceTukeroORG;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParcerThreadInitializer  {

    private UtilsWebServiceTukeroORG parserService;
    @Autowired
    private void setParserService(UtilsWebServiceTukeroORG parserService) {
        this.parserService = parserService;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        parserService.registerUtilsWebService();
    }
}
