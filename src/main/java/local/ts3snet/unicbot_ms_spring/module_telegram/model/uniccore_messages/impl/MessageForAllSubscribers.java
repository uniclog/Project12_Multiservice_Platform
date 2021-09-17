package local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "unicCoreMessage_MessageForAllSubscribers")
public class MessageForAllSubscribers extends UnicBotCoreMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... msg) {
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
