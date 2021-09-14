package local.ts3snet.unicbot_ms_spring.module_teamspeak_utils.service.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotCoreTelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeamspeakUtils extends TS3EventAdapter {
    private int clientId;
    private TS3Api api;

    public void setApi(TS3Api api) {
        this.api = api;
        this.clientId = api.whoAmI().getId();
    }

    private UnicBotCoreTelegramBotService telegramUnicBotCoreUserService;
    @Autowired
    public void setUnicBotCoreTelegramBotService(@Qualifier(value = "unicBotCoreTelegramBotServiceImpl") UnicBotCoreTelegramBotService tg) {
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
        log.info(e.getClientNickname() + " joined server");
    }
    @Override
    public void onClientLeave(ClientLeaveEvent e) {
        //telegramUnicBotCoreUserService.sendMessageForAllSubscribers(user.getuName() + " left server");
        //log.info(e.getClientNickname() + " joined server");
    }
    @Override
    public void onTextMessage(TextMessageEvent e) {
        if (e.getTargetMode() == TextMessageTargetMode.CHANNEL && e.getInvokerId() != clientId) {
            api.sendChannelMessage("Hello " + e.getInvokerName() + "!");
        }
    }
}
