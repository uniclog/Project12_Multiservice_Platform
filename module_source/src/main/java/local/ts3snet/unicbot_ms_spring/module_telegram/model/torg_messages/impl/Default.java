package local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
public class Default extends UnicBotTORGMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... param) {
        log.debug("-> default");

        bot.sendMessage(this.getUserId(),
                "Привет ... \nПосмотри что умею: /help");
    }

    @Override
    public String messageType() {
        return "default";
    }
}
