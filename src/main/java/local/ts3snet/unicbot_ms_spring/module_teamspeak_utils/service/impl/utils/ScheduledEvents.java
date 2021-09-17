package local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.service.impl.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.service.TeamspeakBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;

import static com.github.theholywaffle.teamspeak3.api.ChannelProperty.CHANNEL_NAME;

/**
 * Scheduled Events for Teamspeak3 server
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduledEvents {

    // @Autowired TeamspeakBotService
    private final TeamspeakBotService teamspeakBotServiceA;

    private TS3Api api;

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        api = teamspeakBotServiceA.getApi();
    }

    /**
     * Task for time update (rate = 60 sec.)
     * Set time channel tittle = time now
     */
    @Scheduled(fixedRate = 60000, initialDelay = 10000)
    public void timeUpdate() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("HH:mm");
        String formatTime = formatForDateNow.format(date);

        EnumMap<ChannelProperty, String> options = new EnumMap<>(ChannelProperty.class);
        options.put(CHANNEL_NAME, "[spacer.time]   TIME : " + formatTime + "  UTC+9");
        api.editChannel(22, options);

        log.debug(" --> time update");
    }
}
