package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageInterface;
import local.uniclog.frame_dataaccess.service.TelegramMyFitnessUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@Data
@Slf4j
public abstract class UniclogMyFitnessMessageAbstract implements MessageInterface {
    private String textMessage;
    private Long userId;
    private String userName;
    private Integer messageId;

    private TelegramMyFitnessUserService telegramMyFitnessUserService;
    @Autowired
    public void setTelegramTORGUserService(TelegramMyFitnessUserService telegramMyFitnessUserService) {
        this.telegramMyFitnessUserService = telegramMyFitnessUserService;
    }

    public void printDebugLog() {
        log.debug("msg: " + userId + " >> " + messageType());
    }

    public String convertToUTF8(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }
}
