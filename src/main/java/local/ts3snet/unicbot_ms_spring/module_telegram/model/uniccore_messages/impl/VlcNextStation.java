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
@Component(value = "unicVlcNextStation")
public class VlcNextStation extends UnicBotCoreMessageAbstract {

    private VlcControlService vlc;
    @Autowired
    private void setVlcService(VlcControlService vlc) {
        this.vlc = vlc;
    }

    /**
     * Next music station in playlist (send http request on vlc server)
     * @param bot bot signature
     * @param msg user messages
     */
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        vlc.nextStation();
        bot.sendMessage(this.getUserId(), "Next: " + vlc.getStationName());
    }

    @Override
    public String messageType() {
        return MessageType.NEXT;
    }
}
