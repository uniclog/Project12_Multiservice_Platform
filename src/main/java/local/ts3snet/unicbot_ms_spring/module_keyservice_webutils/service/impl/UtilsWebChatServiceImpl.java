package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service.impl;

import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.config.KeyModuleWebConfig;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UtilsWebChatServiceImpl implements UtilsWebChatService {
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

    public UtilsWebChatServiceImpl() {
        log.info("UtilsWebParserService registered...");
    }

    @Override
    public void registerUtilsKeyModuleService() {
        log.info("UtilsWebParserService entry task");
        // something here ...
    }


    //@Scheduled(fixedRateString = "${web.chat.update.rate:30}000")
    @Scheduled(fixedRateString = "#{@keyModuleWebConfig.getUpdateRate()}")
    private void serviceUpdateRate() {
        log.debug("UtilsWebParserService.serviceUpdateRate() get new messages...");
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
    }
}