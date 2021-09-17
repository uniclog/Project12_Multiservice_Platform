package local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages;

import local.ts3snet.unicbot_ms_spring.core.service.TelegramTORGUserService;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageInterface;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@Data
public abstract class UnicBotTORGMessageAbstract implements MessageInterface {
    private String textMessage;
    private Long userId;
    private String userName;

    private TelegramTORGUserService telegramTORGUserService;
    @Autowired
    public void setTelegramTORGUserService(TelegramTORGUserService telegramTORGUserService) {
        this.telegramTORGUserService = telegramTORGUserService;
    }

    public String convertToUTF8(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }
}
