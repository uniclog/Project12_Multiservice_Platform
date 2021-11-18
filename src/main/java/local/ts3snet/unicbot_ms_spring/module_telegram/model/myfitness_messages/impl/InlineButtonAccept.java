package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramMyFitnessUserEntity;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component(value = "myFitnessMessageInlineButtonAccept")
public class InlineButtonAccept extends UniclogMyFitnessMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        TelegramMyFitnessUserEntity user = this.getTelegramMyFitnessUserService().findByUserTelegramId(this.getUserId());
        if(user != null) {
            int waterCount = user.getWaterCount() + 1;
            user.setWaterCount(waterCount);
            this.getTelegramMyFitnessUserService().update(user);

            bot.editMessageText(this.getUserId(), this.getMessageId(),
                    "Подтверждено. Сегодня вы пили воду "+ waterCount + " " +
                            ((waterCount < 2 || waterCount > 4) ? "раз": "раза"));
        }  else {
            bot.editMessageText(this.getUserId(), this.getMessageId(),
                    "Не подтверждено");
        }
    }

    @Override
    public boolean serviceMessage() {
        return true;
    }

    @Override
    public String messageType() {
        return MessageType.INLINE_BUTTON_ACCEPT.getTextValue();
    }
}