package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramUnicBotCoreUserEntity;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "unicCoreMessageSub")
public class Sub extends UnicBotCoreMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        TelegramUnicBotCoreUserEntity user = new TelegramUnicBotCoreUserEntity();
        user.setUserTelegramId(this.getUserId());
        user.setSubscriber(true);
        user.setUserName(this.getUserName());

        getTelegramUnicBotCoreUserService().save(user);
        bot.sendMessage(this.getUserId(), "-> sub on channel");

        log.debug("-> sub");
    }

    @Override
    public String messageType() {
        return "/sub";
    }
}
