package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.api;

import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service.UtilsWebChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Initializer Web Chat Module service
 */
@Slf4j
@Component
public class UtilsKeyModuleThreadInitializer {

    private UtilsWebChatService utilsWebChatService;
    @Autowired
    private void setUtilsWebChatService(UtilsWebChatService parserService) {
        this.utilsWebChatService = parserService;
    }

    @EventListener({ApplicationReadyEvent.class})
    public void init() {
        utilsWebChatService.registerUtilsKeyModuleService();
    }
}
