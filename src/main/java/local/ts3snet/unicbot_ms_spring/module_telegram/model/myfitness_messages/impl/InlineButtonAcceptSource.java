package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Lazy
@Component(value = "myFitnessMessageInlineButtonAcceptSource")
public class InlineButtonAcceptSource extends UniclogMyFitnessMessageAbstract {

    @Override
    public void execute(TelegramBotService bot, String... msg) {

        getTelegramMyFitnessUserService().findAllSubscribers().forEach(e ->
                bot.sendInlineKeyboard(e.getUserTelegramId(),
                        "Подтвердить действие",
                        Map.of(MessageType.INLINE_BUTTON_ACCEPT.getTextValue(), "Подтвердить"))
        );

        printDebugLog();
    }

    @Override
    public boolean serviceMessage() {
        return true;
    }

    @Override
    public String messageType() {
        return MessageType.INLINE_BUTTON_ACCEPT_SOURCE.getTextValue();
    }
}