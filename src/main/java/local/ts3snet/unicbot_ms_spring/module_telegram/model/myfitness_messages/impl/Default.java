package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component(value = "myFitnessMessageDefault")
public class Default extends UniclogMyFitnessMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        bot.sendMessage(this.getUserId(),
                "Привет ..."
                        + "\nПосмотри что умею: /help");
    }

    @Override
    public String messageType() {
        return MessageType.DEFAULT.getTextValue();
    }
}
