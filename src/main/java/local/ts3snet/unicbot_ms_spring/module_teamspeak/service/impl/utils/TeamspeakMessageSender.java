package local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.TeamspeakMessageInterface;

public interface TeamspeakMessageSender {
    void setApi(TS3Api api);
    void sendChannelMessage(Integer channelId, TeamspeakMessageInterface message);
}
