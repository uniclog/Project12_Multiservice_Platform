package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.torg_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.torg_messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
public class MessageForAllSubscribers extends UnicBotTORGMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... param) {
        getTelegramTORGUserService().findAllSubscribers().forEach(e ->
                bot.sendMessage(e.getUserTelegramId(), param[0])
        );

        log.debug("-> messageForAllSubscribers");
    }

    @Override
    public String messageType() {
        return "messageForAllSubscribers";
    }
}
