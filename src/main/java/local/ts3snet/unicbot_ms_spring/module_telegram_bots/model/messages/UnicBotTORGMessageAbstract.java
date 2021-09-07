package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages;

import local.ts3snet.unicbot_ms_spring.core.service.CoreUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public abstract class UnicBotTORGMessageAbstract implements UnicBotTORGMessageInterface {
    private String textMessage;
    private Long userId;
    private String userName;

    private CoreUserService coreUserService;
    @Autowired
    public void setCoreUserService(CoreUserService coreUserService) {
        this.coreUserService = coreUserService;
    }
}
