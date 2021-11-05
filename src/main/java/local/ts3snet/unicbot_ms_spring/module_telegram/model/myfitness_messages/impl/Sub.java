package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramMyFitnessUserEntity;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "myFitnessMessageSub")
public class Sub extends UniclogMyFitnessMessageAbstract {
    /**
     * Subscribe on bot
     * @param bot bot signature
     * @param msg user messages
     */
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        TelegramMyFitnessUserEntity user = new TelegramMyFitnessUserEntity();
        user.setUserTelegramId(this.getUserId());
        user.setSubscriber(true);
        user.setUserName(this.getUserName());

        getTelegramMyFitnessUserService().save(user);
        bot.sendMessage(this.getUserId(), "-> sub on channel");

        printDebugLog();
    }

    @Override
    public String messageType() {
        return MessageType.SUB;
    }
}
