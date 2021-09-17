package local.ts3snet.unicbot_ms_spring.module_telegram.api;

import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Slf4j
@Component
public class ApiTelegramBotsInitializer {
    private final List<TelegramBotService> bots;

    public ApiTelegramBotsInitializer(List<TelegramBotService> bots) {
        this.bots = bots;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        bots.forEach(bot -> {
            try {
                telegramBotsApi.registerBot(bot);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        });
        log.info("Bots registered...");
    }
}
