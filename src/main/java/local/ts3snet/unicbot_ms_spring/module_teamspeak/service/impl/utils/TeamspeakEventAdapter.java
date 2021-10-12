package local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;

public interface TeamspeakEventAdapter extends TS3Listener {
    void setApi(TS3Api api);
}
