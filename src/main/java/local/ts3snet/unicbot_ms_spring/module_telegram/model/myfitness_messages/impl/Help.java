package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component(value = "myFitnessMessageHelp")
public class Help extends UniclogMyFitnessMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        bot.sendMessage(this.getUserId(), convertToUTF8(
                "вот что умею"
                        + "\n /sub подписаться на уведомления от бота"
                        + "\n каждый час с 10:00 до 22:00 +3"
                        + "\n /unsub отписаться")
        );
    }

    @Override
    public String messageType() {
        return MessageType.HELP;
    }
}
