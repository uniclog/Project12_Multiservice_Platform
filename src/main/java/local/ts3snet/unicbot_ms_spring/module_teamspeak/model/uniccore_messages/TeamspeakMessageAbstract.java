package local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages;

import local.ts3snet.unicbot_ms_spring.core.service.TeamspeakUserService;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.TeamspeakMessageInterface;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.TeamspeakBotService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@Data
@Slf4j
public abstract class TeamspeakMessageAbstract implements TeamspeakMessageInterface {
    private String textMessage;
    private Long userId;
    private String userName;

    private TeamspeakUserService teamspeakUserService;
    @Autowired
    public void setTelegramTORGUserService(TeamspeakUserService teamspeakUserService) {
        this.teamspeakUserService = teamspeakUserService;
    }

    public void printDebugLog() {
        log.debug("msg: " + userId + " >> " + messageType());
    }

    public String convertToUTF8(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }

    @Override
    public String getMessageText() {
        return this.textMessage;
    }
}
