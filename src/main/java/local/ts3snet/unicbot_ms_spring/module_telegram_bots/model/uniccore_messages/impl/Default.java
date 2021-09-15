package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.TelegramBotService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component(value = "unicCoreMessageDefault")
public class Default extends UnicBotCoreMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        bot.sendMessage(this.getUserId(), "Привет ...");
    }

    @Override
    public String messageType() {
        return "default";
    }
}
