package local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.impl;

import com.github.theholywaffle.teamspeak3.TS3Api;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeamspeakMessageSenderImpl implements TeamspeakMessageSender {
    private TS3Api api;

    /**
     * Update Api if reconnect
     * @param api teamspeak api
     */
    @Override
    public void setApi(TS3Api api) {
        this.api = api;
    }

    @Override
    public void sendPrivateMessage(Integer clientId, String message) {
        api.sendPrivateMessage(clientId, message);
        System.out.println(clientId);
        System.out.println(message);
    }

    @Override
    public void sendChannelMessage(Integer channelId, String message) {
        if (channelId != null)
            api.sendChannelMessage(channelId, message);
        api.sendChannelMessage(message);
    }

    @Override
    public void sendChannelMessage(String message) {
        api.sendChannelMessage(message);
    }
}
