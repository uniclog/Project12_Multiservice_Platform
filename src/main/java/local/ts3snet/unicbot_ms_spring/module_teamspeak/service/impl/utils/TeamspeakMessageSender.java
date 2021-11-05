package local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;

public interface TeamspeakMessageSender {
    void setApi(TS3Api api);
    void sendChannelMessage(Integer channelId, String message);
    void sendChannelMessage(String message);
    void sendPrivateMessage(Integer clientId, String message);
}
