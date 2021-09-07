package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
public class Default extends UnicBotTORGMessageAbstract {
    @Override
    public void execute(UnicBotTORGTelegramBotService bot, String... param) {
        log.debug("-> default");

        bot.sendMessage(this.getUserId(), "Привет ...");
    }

    @Override
    public String messageType() {
        return "default";
    }
}
