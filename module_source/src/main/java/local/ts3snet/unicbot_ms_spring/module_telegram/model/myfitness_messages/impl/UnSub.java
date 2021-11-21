package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramMyFitnessUserEntity;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "myFitnessMessageUnSub")
public class UnSub extends UniclogMyFitnessMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... param) {
        TelegramMyFitnessUserEntity user = new TelegramMyFitnessUserEntity();
        user.setUserTelegramId(this.getUserId());
        user.setSubscriber(false);
        user.setUserName(this.getUserName());

        getTelegramMyFitnessUserService().update(user);

        log.debug("-> unsub");
    }

    @Override
    public String messageType() {
        return MessageType.UNSUB.getTextValue();
    }
}
