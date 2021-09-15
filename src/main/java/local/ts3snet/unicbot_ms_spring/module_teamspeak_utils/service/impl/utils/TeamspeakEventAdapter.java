package local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.service.impl.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TeamspeakEventAdapter extends TS3EventAdapter {
    private int clientId;
    private TS3Api api;
    private final Map<Integer, ClientInfo> clients = new HashMap<>();

    public void setApi(TS3Api api) {
        this.api = api;
        this.clientId = api.whoAmI().getId();
        this.api.getClients()
                .forEach(c -> clients.put(c.getId(), api.getClientInfo(c.getId())));

        String info = clients
                .entrySet()
                .stream()
                .map(c -> c.getKey() + " - " + c.getValue().getNickname())
                .collect(Collectors.joining(", "));
        log.info("Clients on server: " + info);
    }

    private final TelegramBotService telegramUnicBotCoreUserService;
    public TeamspeakEventAdapter(@Qualifier(value = "unicBotCoreTelegramBotServiceImpl") TelegramBotService tg) {
        this.telegramUnicBotCoreUserService = tg;
    }

    @Override
    public void onClientJoin(ClientJoinEvent e) {
        api.sendPrivateMessage(e.getClientId(),
                " welcome to the party! " +
                        "\n Commands: " +
                        "\n !use loginnotify" +
                        "\n !mytime" +
                        "\n !wakeup");
        // send to telegram
        telegramUnicBotCoreUserService.sendMessageForAllSubscribers(e.getClientNickname() + " joined server");
        ClientInfo client = api.getClientInfo(e.getClientId());
        log.info(client.getNickname() + " joined server");

        clients.put(e.getClientId(), client);
    }
    @Override
    public void onClientLeave(ClientLeaveEvent e) {
        ClientInfo clientInfo = clients.get(e.getClientId());
        telegramUnicBotCoreUserService.sendMessageForAllSubscribers(clientInfo.getNickname() + " left server");
        log.info(clientInfo.getNickname() + " left server");

        clients.remove(e.getClientId());
    }
    @Override
    public void onTextMessage(TextMessageEvent e) {
        if (e.getTargetMode() == TextMessageTargetMode.CHANNEL && e.getInvokerId() != clientId) {
            api.sendChannelMessage("Hello " + e.getInvokerName() + "!");
        }
    }
}
