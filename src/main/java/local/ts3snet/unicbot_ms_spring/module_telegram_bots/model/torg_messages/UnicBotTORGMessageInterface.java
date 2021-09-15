package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.torg_messages;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;

public interface UnicBotTORGMessageInterface {
    void execute(UnicBotTORGTelegramBotService bot, String... msg);
    String messageType();
}
