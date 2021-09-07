package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.CoreUserEntity;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.UnicBotTORGMessageInterface;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.impl.UnicBotTORGTelegramBotServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
public class UnSub extends UnicBotTORGMessageAbstract implements UnicBotTORGMessageInterface {
    @Override
    public void execute(UnicBotTORGTelegramBotServiceImpl bot, String... param) {
        CoreUserEntity user = new CoreUserEntity();
        user.setUserTelegramId(this.getUserId());
        user.setSubscriber(true);
        user.setUserName(this.getUserName());

        getCoreUserService().update(user);

        log.debug("-> unsub");
    }

    @Override
    public String messageType() {
        return "/unsub";
    }
}
