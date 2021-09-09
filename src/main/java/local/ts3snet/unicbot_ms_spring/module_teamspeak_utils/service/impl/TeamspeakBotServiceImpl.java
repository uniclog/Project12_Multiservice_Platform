package local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.service.impl;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.github.theholywaffle.teamspeak3.api.reconnect.ReconnectStrategy;
import local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.config.TeamspeakBotConfig;
import local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.service.TeamspeakBotService;
import local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.service.utils.TeamspeakUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamspeakBotServiceImpl implements TeamspeakBotService {
    private String address;
    private String password;
    private String login;
    @Setter
    private static volatile int clientId;

    private final TeamspeakBotConfig config;
    public TeamspeakBotServiceImpl(TeamspeakBotConfig config) {
        this.config = config;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        this.address = config.getIpAddress();
        this.login = config.getLogin();
        this.password = config.getPassword();

        this.register();
    }

    @Override
    public void register() {
        final TS3Config cfg = new TS3Config();
        cfg.setHost(address);
        cfg.setEnableCommunicationsLogging(true);
        cfg.setReconnectStrategy(ReconnectStrategy.constantBackoff());
        cfg.setConnectionHandler(new ConnectionHandler() {
            @Override
            public void onConnect(TS3Api api) {
                stuffThatNeedsToRunEveryTimeTheQueryConnects(api);
            }

            @Override
            public void onDisconnect(TS3Query ts3Query) {
                // Nothing
            }
        });
        final TS3Query query = new TS3Query(cfg);
        query.connect();

        stuffThatOnlyEverNeedsToBeRunOnce(query.getApi());
        query.exit();
    }

    private void stuffThatNeedsToRunEveryTimeTheQueryConnects(TS3Api api) {
        api.login(this.login, this.password);
        api.selectVirtualServerById(1);
        api.setNickname(login);
        api.moveClient(clientId, 32);
        api.registerAllEvents();
        // may be this case TS3EventType.TEXT_CHANNEL
        clientId = api.whoAmI().getId();
    }

    private static void stuffThatOnlyEverNeedsToBeRunOnce(final TS3Api api) {
        // We only want to greet people once
        api.sendChannelMessage(" is online!");

        // On the API side of things, you only need to register your TS3Listeners once!
        // These are not affected when the query disconnects.
        api.addTS3Listeners(new TeamspeakUtils(api));
    }

}
