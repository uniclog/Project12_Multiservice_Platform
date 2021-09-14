package local.ts3snet.unicbot_ms_spring.module_telegram_bots.api;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotCoreTelegramBotService;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Component
public class ApiTelegramBotsInitializer {
    private final UnicBotCoreTelegramBotService unicBotCore;
    private final UnicBotTORGTelegramBotService botTorg;

    public ApiTelegramBotsInitializer(
            @Qualifier("unicBotCoreTelegramBotServiceImpl") UnicBotCoreTelegramBotService coreBot,
            @Qualifier("UnicBotTORGTelegramBotServiceImpl") UnicBotTORGTelegramBotService torgBot) {
        this.unicBotCore = coreBot;
        this.botTorg = torgBot;
    }


    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(botTorg);
            telegramBotsApi.registerBot(unicBotCore);
            log.info("Bots registered...");
        } catch (TelegramApiRequestException e) {
            log.error(e.getMessage());
        }
    }
}
