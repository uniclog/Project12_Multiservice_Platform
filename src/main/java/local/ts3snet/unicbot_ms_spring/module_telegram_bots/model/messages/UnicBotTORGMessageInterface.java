package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.impl.UnicBotTORGTelegramBotServiceImpl;

public interface UnicBotTORGMessageInterface {
    void execute(UnicBotTORGTelegramBotServiceImpl bot, String... msg);
    String messageType();
}
