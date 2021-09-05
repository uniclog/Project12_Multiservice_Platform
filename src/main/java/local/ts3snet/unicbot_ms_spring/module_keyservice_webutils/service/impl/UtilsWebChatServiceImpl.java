package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service.impl;

import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.entity.KeyDataEntity;
import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.model.WebChat;
import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service.KeyDataService;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;
import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service.UtilsWebChatService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UtilsWebChatServiceImpl implements Runnable, UtilsWebChatService {
    @Getter
    @Setter
    private static volatile boolean loophole = true;

    private UnicBotTORGTelegramBotService unicBotTORGTelegramBotService;
    @Autowired
    public void setUnicBotTORGTelegramBotService(@Qualifier("UnicBotTORGTelegramBotServiceImpl") UnicBotTORGTelegramBotService bot) {
        this.unicBotTORGTelegramBotService = bot;
    }

    private KeyDataService keyDataService;
    @Autowired
    public void setKeyDataService(KeyDataService keyDataService) {
        this.keyDataService = keyDataService;
    }

    private WebChat webChat;
    @Autowired
    private void setWebChat(WebChat webChat) {
        this.webChat = webChat;
    }

    @Override
    public void registerUtilsKeyModuleService() {
        this.run();
        log.info("UtilsWebParserService registered...");
    }

    @Override
    public void run() {
        while (loophole) {
            try {
                webChat.update().forEach(e -> {
                    log.info(e.toString());
                    unicBotTORGTelegramBotService.sendMessageForAllSubscribers(e.toString());

                    List<String> keys = webChat.parseKey(e.toString());
                    if (!keys.isEmpty()) {
                        keys.forEach(key -> {
                            KeyDataEntity keyEntity = new KeyDataEntity();
                            keyEntity.setKey(key);

                            keyDataService.save(keyEntity);
                        });
                    }
                });

                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}