package local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.service.VlcControlService;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = false)
@Lazy
@Component(value = "unicVlcPrevStation")
public class VlcPrevStation extends UnicBotCoreMessageAbstract {

    private VlcControlService vlc;
    @Autowired
    private void setVlcService(VlcControlService vlc) {
        this.vlc = vlc;
    }

    /**
     * Prev music station in playlist (send http request on vlc server)
     * @param bot bot signature
     * @param msg user messages
     */
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        vlc.prevStation();
        bot.sendMessage(this.getUserId(), "Prev: " + vlc.getStationName());
    }

    @Override
    public String messageType() {
        return MessageType.PREV;
    }
}
