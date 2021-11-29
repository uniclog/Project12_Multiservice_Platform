package local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.service.KeyDataService;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
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
    public void execute(TelegramBotService bot, String... param) {
        log.debug("-> getkeys");
        StringBuilder keys = new StringBuilder();
        keyDataService.findAll().forEach(k -> keys.append("\n")
                .append(k.getDate().getDayOfMonth()).append(".")
                .append(k.getDate().getMonthValue()).append(".").append(k.getDate().getYear()).append("\n")
                .append(k.getKey()));

        bot.sendMessage(this.getUserId(),
                "Ключи: \n" + (keys.toString().equals("") ? "N/D" : keys));
    }

    @Override
    public String messageType() {
        return "/getkeys";
    }
}