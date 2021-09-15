package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotCoreTelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "unicCoreMessage_MessageForAllSubscribers")
public class MessageForAllSubscribers extends UnicBotCoreMessageAbstract {
    @Override
    public void execute(UnicBotCoreTelegramBotService bot, String... msg) {
        getTelegramUnicBotCoreUserService().findAllSubscribers().forEach(e ->
                bot.sendMessage(e.getUserTelegramId(), msg[0])
        );

        log.debug("-> messageForAllSubscribers");
    }

    @Override
    public String messageType() {
        return "messageForAllSubscribers";
    }
}
