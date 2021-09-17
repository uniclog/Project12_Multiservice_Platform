package local.ts3snet.unicbot_ms_spring.module_telegram.model;

import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;

public interface MessageInterface {
    void execute(TelegramBotService bot, String... msg);
    String messageType();
}