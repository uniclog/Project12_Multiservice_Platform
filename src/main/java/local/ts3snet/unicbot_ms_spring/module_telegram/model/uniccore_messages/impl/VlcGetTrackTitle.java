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
@Component(value = "unicVlcGetTrackTitle")
public class VlcGetTrackTitle extends UnicBotCoreMessageAbstract {

    private VlcControlService vlc;
    @Autowired
    private void setVlcService(VlcControlService vlc) {
        this.vlc = vlc;
    }

    /**
     * Get track title from vlc server
     * @param bot bot signature
     * @param msg user messages
     */
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        bot.sendMessage(this.getUserId(), "Track: " + vlc.getTrackName());
    }

    @Override
    public String messageType() {
        return MessageType.TRACK;
    }
}
