package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.TelegramBotService;

public interface MessageInterface {
    void execute(TelegramBotService bot, String... msg);
    String messageType();
}