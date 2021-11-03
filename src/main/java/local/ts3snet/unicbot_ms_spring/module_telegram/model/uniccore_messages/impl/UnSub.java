package local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramUnicBotCoreUserEntity;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "unicCoreMessageUnSub")
public class UnSub extends UnicBotCoreMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... param) {
        TelegramUnicBotCoreUserEntity user = new TelegramUnicBotCoreUserEntity();
        user.setUserTelegramId(this.getUserId());
        user.setSubscriber(false);
        user.setUserName(this.getUserName());

        getTelegramUnicBotCoreUserService().update(user);

        log.debug("-> unsub");
    }

    @Override
    public String messageType() {
        return MessageType.UNSUB;
    }
}
