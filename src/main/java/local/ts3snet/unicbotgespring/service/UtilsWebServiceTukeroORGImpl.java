package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.entity.SetKeyEntity;
import local.ts3snet.unicbotgespring.model.WebChat;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UtilsWebServiceTukeroORGImpl implements Runnable, UtilsWebServiceTukeroORG{
    @Getter
    @Setter
    private static volatile boolean loophole = true;

    private TelegramBotService telegramBotService;
    @Autowired
    public void setTelegramBot(@Qualifier("telegrambotservice") TelegramBotService bot) {
        this.telegramBotService = bot;
    }

    private SetKeyService setKeyService;
    @Autowired
    public void setSetKeyService(SetKeyService setKeyService) {
        this.setKeyService = setKeyService;
    }

    private WebChat webChat;
    @Autowired
    private void setWebChat(WebChat webChat) {
        this.webChat = webChat;
    }

    @Override
    public void registerUtilsWebService() {
        this.run();
        log.info("UtilsWebParserService registered...");
    }

    @Override
    public void run() {
        while (loophole) {
            try {
                webChat.update().forEach(e -> {
                    log.info(e.toString());
                    telegramBotService.sendMessageForAllSubscribers(e.toString());

                    List<String> keys = webChat.parseKey(e.toString());
                    if (!keys.isEmpty()) {
                        keys.forEach(key -> {
                            SetKeyEntity keyEntity = new SetKeyEntity();
                            keyEntity.setKey(key);
                            Date date = new Date(System.currentTimeMillis());
                            keyEntity.setDate(date);
                            setKeyService.save(keyEntity);
                        });

                        setKeyService.save(new SetKeyEntity());
                    }
                });

                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}