package local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.TeamspeakBotService;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.service.VlcControlService;
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
import java.util.Map;

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
    // @Autowired TeamspeakBotService
    private final VlcControlService vlcControlService;

    private TS3Api api;

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        api = teamspeakBotServiceA.getApi();
    }

    public void setApi(TS3Api api) {
        this.api = api;
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

    /**
     * Task for radio data update (rate = 15 sec.)
     * Set radio info channels tittle = track now
     */
    private String station = "unknown";
    private String track = "unknown";
    @Scheduled(fixedRate = 15000, initialDelay = 10000)
    public void radioInfoUpdate() {
        String temp = "Station: " + vlcControlService.getStationName()
                .replaceAll("[^0-9A-Za-zа-яА-Я_ -]", "");
        if (temp.length() >40) temp = temp.substring(0, 39);
        if (!temp.equals(station)){
            Map<ChannelProperty, String> optionsC1 = new EnumMap<>(ChannelProperty.class);
            optionsC1.put(CHANNEL_NAME, temp);
            api.editChannel(33, optionsC1);
            station = temp;
            if (!station.equals("Station:"))
                log.info("Station: " + station);
        }
        temp = "Track: " + vlcControlService.getTrackName()
                .replaceAll("[^0-9A-Za-zа-яА-Я_ -]", "")
                .replace("amp", "");
        if (temp.length() >40) temp = temp.substring(0, 39);
        if (!temp.equals(track)){
            Map<ChannelProperty, String> optionsC1 = new EnumMap<>(ChannelProperty.class);
            optionsC1.put(CHANNEL_NAME,  temp);
            api.editChannel(31, optionsC1);
            track = temp;
            if (!track.equals("Track:  -"))
                log.info(track);
        }
    }
}
