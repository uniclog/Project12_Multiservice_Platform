package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotCoreTelegramBotService;

public interface UnicBotCoreMessageInterface {
    void execute(UnicBotCoreTelegramBotService bot, String... msg);
    String messageType();
}
