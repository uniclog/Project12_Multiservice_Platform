package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.impl;

import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service.KeyDataService;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Lazy
@Component
public class GetKeys extends UnicBotTORGMessageAbstract {
    private KeyDataService keyDataService;
    @Autowired
    public void setKeyDataService(KeyDataService keyDataService) {
        this.keyDataService = keyDataService;
    }

    @Override
    public void execute(UnicBotTORGTelegramBotService bot, String... param) {
        log.debug("-> getkeys");
        StringBuilder keys = new StringBuilder();
        keyDataService.findAll().forEach(k -> keys.append("\n").append(k.getKey()));

        bot.sendMessage(this.getUserId(),
                "Ключи: \n" + (keys.toString().equals("") ? "N/D" : keys));
    }

    @Override
    public String messageType() {
        return "/getkeys";
    }
}