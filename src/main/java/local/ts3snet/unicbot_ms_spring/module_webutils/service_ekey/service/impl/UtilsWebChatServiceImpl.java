package local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.service.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.entity.KeyDataEntity;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.model.WebChat;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.service.KeyDataService;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.service.UtilsWebChatService;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class UtilsWebChatServiceImpl implements UtilsWebChatService {
    @Getter
    @Setter
    private static volatile boolean loophole = true;

    private TelegramBotService unicBotTORGTelegramBotService;
    @Autowired
    public void setUnicBotTORGTelegramBotService(@Qualifier("unicBotTORGTelegramBotServiceImpl") TelegramBotService bot) {
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

    @PostConstruct
    public void utilsWebChatServiceImplInit() {
        log.info("UtilsWebParserService registered...");
    }

    //@Scheduled(fixedRateString = "${web.chat.update.rate:30}000")
    @Scheduled(fixedRateString = "#{@keyModuleWebConfig.getUpdateRate()}", initialDelay = 10000)
    private void serviceUpdateRate() {
        log.debug("UtilsWebParserService.serviceUpdateRate() get new messages...");
        webChat.update().forEach(e -> {
            log.info(e.toString());
            unicBotTORGTelegramBotService.sendMessageForAllSubscribers(null, e.toString());

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