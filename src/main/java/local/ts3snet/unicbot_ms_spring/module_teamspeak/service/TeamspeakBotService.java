package local.ts3snet.unicbot_ms_spring.module_teamspeak.service;

import com.github.theholywaffle.teamspeak3.TS3Api;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.TeamspeakMessageInterface;

public interface TeamspeakBotService {
    TS3Api getApi();
    /*void sendChannelMessage(Integer channelId, TeamspeakMessageInterface message);
    void sendPrivateMessage(Integer clientId, TeamspeakMessageInterface message);*/
}