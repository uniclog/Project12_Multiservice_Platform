package local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.TeamspeakMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.service.VlcControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Lazy
@Component(value = "ts3sMessagePrev")
public class VlcPrevStation extends TeamspeakMessageAbstract {
    private VlcControlService vlc;
    @Autowired
    private void setVlcService(VlcControlService vlc) {
        this.vlc = vlc;
    }

    /**
     * Go to Next station and get station title
     *
     * @param bot bot sender service
     * @param msg station number
     */
    @Override
    public void execute(TeamspeakMessageSender bot, String... msg) {
        vlc.prevStation();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        bot.sendChannelMessage("Next: " + vlc.getStationName());
    }

    @Override
    public String messageType() {
        return MessageType.PREV.getTextValue();
    }
}