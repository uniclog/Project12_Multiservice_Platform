package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages;

import local.ts3snet.unicbot_ms_spring.core.service.TelegramTORGUserService;
import local.ts3snet.unicbot_ms_spring.core.service.TelegramUnicBotCoreUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@Data
public abstract class UnicBotCoreMessageAbstract implements UnicBotCoreMessageInterface {
    private String textMessage;
    private Long userId;
    private String userName;

    private TelegramUnicBotCoreUserService telegramUnicBotCoreUserService;
    @Autowired
    public void setTelegramTORGUserService(TelegramUnicBotCoreUserService telegramUnicBotCoreUserService) {
        this.telegramUnicBotCoreUserService = telegramUnicBotCoreUserService;
    }

    public String convertToUTF8(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }
}
