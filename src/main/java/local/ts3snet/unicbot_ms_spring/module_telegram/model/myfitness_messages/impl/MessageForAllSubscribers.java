package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "myFitnessMessage_MessageForAllSubscribers")
public class MessageForAllSubscribers extends UniclogMyFitnessMessageAbstract {

    /**
     * Send message for all users
     * @param bot bot signature
     * @param msg user messages
     */
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        getTelegramMyFitnessUserService().findAllSubscribers().forEach(e ->
                bot.sendMessage(e.getUserTelegramId(), msg[0])
        );

        printDebugLog();
    }

    @Override
    public boolean serviceMessage() {
        return true;
    }

    @Override
    public String messageType() {
        return MessageType.MESSAGE_FOR_ALL_SUBSCRIBERS.getTextValue();
    }
}
