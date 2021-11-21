package local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import local.uniclog.frame_dataaccess.entity.TelegramTORGUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
public class Sub extends UnicBotTORGMessageAbstract {
    @Override
    public void execute(TelegramBotService bot, String... param) {
        TelegramTORGUserEntity user = new TelegramTORGUserEntity();
        user.setUserTelegramId(this.getUserId());
        user.setSubscriber(true);
        user.setUserName(this.getUserName());

        getTelegramTORGUserService().save(user);

        log.debug("-> sub");
    }

    @Override
    public String messageType() {
        return "/sub";
    }
}
