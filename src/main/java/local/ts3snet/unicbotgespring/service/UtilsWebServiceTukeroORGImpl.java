package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.model.WebChat;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UtilsWebServiceTukeroORGImpl implements Runnable, UtilsWebServiceTukeroORG{
    @Getter
    @Setter
    private static volatile boolean loophole = true;

    TelegramBotService telegramBotService;
    @Autowired
    public void setTelegramBot(@Qualifier("telegrambotservice") TelegramBotService bot) {
        this.telegramBotService = bot;
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
                });

                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}