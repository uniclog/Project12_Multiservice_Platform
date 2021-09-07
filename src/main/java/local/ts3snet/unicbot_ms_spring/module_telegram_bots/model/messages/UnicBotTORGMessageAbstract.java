package local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages;

import local.ts3snet.unicbot_ms_spring.core.service.CoreUserService;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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

    /*private UnicBotTORGTelegramBotService unicBotTORGTelegramBotService;
    @Autowired
    public void setUnicBotTORGTelegramBotService(@Qualifier("UnicBotTORGTelegramBotServiceImpl") UnicBotTORGTelegramBotService bot) {
        this.unicBotTORGTelegramBotService = bot;
    }*/
}
