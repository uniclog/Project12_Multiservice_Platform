package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotCoreTeamspeakTelegramBotService;

public interface UnicBotCoreMessageInterface {
    void execute(UnicBotCoreTeamspeakTelegramBotService bot, String... msg);
    String messageType();
}
