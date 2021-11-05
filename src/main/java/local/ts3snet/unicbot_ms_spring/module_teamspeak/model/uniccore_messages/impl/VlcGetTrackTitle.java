package local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.TeamspeakMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.service.VlcControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "ts3sMessageTrack")
public class VlcGetTrackTitle extends TeamspeakMessageAbstract {
    private VlcControlService vlc;
    @Autowired
    private void setVlcService(VlcControlService vlc) {
        this.vlc = vlc;
    }

    @Override
    public void execute(TeamspeakMessageSender bot, String... msg) {
        bot.sendChannelMessage(this.getUserId(), "Track: " + vlc.getTrackName());
    }

    @Override
    public String messageType() {
        return MessageType.TRACK.getTextValue();
    }
}