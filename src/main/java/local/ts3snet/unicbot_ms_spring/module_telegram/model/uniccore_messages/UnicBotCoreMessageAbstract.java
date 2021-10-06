package local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages;

import local.ts3snet.unicbot_ms_spring.core.service.TelegramUnicBotCoreUserService;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageInterface;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@Data
@Slf4j
public abstract class UnicBotCoreMessageAbstract implements MessageInterface {
    private String textMessage;
    private Long userId;
    private String userName;

    private TelegramUnicBotCoreUserService telegramUnicBotCoreUserService;
    @Autowired
    public void setTelegramTORGUserService(TelegramUnicBotCoreUserService telegramUnicBotCoreUserService) {
        this.telegramUnicBotCoreUserService = telegramUnicBotCoreUserService;
    }

    public void printDebugLog() {
        log.debug("msg: " + userId + " >> " + messageType());
    }

    public String convertToUTF8(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }
}
