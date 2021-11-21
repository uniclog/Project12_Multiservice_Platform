package local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.github.theholywaffle.teamspeak3.api.reconnect.ReconnectStrategy;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.config.TeamspeakBotConfig;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.TeamspeakBotService;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakEventAdapter;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Slf4j
@Service
public class TeamspeakBotServiceImpl implements TeamspeakBotService {
    private final String address;
    private final String password;
    private final String login;

    private TS3Api api;

    private final TeamspeakEventAdapter teamspeakEventAdapter;
    private final TeamspeakMessageSender teamspeakMessageSender;

    public TeamspeakBotServiceImpl(
            TeamspeakBotConfig config,
            TeamspeakEventAdapter teamspeakEventAdapter,
            TeamspeakMessageSender teamspeakMessageSender) {
        this.teamspeakEventAdapter = teamspeakEventAdapter;
        this.teamspeakMessageSender = teamspeakMessageSender;

        address = config.getIpAddress();
        login = config.getLogin();
        password = config.getPassword();

        log.info("TeamspeakBotService init");
        log.debug("address=" + address + "; password=" + password + "; login=" + login);
    }


    @EventListener({ContextRefreshedEvent.class})
    public void register() {
        final TS3Config cfg = new TS3Config();
        cfg.setHost(address);
        cfg.setDebugLevel(Level.OFF);
        cfg.setReconnectStrategy(ReconnectStrategy.constantBackoff());
        cfg.setConnectionHandler(new ConnectionHandler() {
            @Override
            public void onConnect(TS3Query ts3Query) {
                stuffThatNeedsToRunEveryTimeTheQueryConnects(ts3Query.getApi());
            }
            @Override
            public void onDisconnect(TS3Query ts3Query) {
                log.info("Teamspeak server is offline");
            }
        });
        final TS3Query query = new TS3Query(cfg);
        query.connect();
        stuffThatOnlyEverNeedsToBeRunOnce(query.getApi());
    }

    private void stuffThatNeedsToRunEveryTimeTheQueryConnects(TS3Api api) {
        api.selectVirtualServerById(1);
        api.login(login, password);
        int clientId = api.whoAmI().getId();
        api.moveClient(clientId, 32);
        api.registerAllEvents();
        api.setNickname(login);

        teamspeakEventAdapter.setApi(api);
        teamspeakMessageSender.setApi(api);
    }

    private void stuffThatOnlyEverNeedsToBeRunOnce(TS3Api api) {
        // On the API side of things
        // These are not affected when the query disconnects.
        this.api = api;
        api.addTS3Listeners(teamspeakEventAdapter);
    }

    @Override
    public TS3Api getApi() {
        return this.api;
    }

    /*@Override
    public void sendChannelMessage(Integer channelId, TeamspeakMessageInterface message) {
        if (channelId == null)
            teamspeakMessageSender.sendChannelMessage(message);
        teamspeakMessageSender.sendChannelMessage(channelId, message);
    }

    @Override
    public void sendPrivateMessage(Integer channelId, TeamspeakMessageInterface message) {
        teamspeakMessageSender.sendPrivateMessage(channelId, message);
    }*/
}
