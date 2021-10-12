package local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.TeamspeakMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.TeamspeakBotService;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component(value = "ts3sMessageDefault")
public class Default extends TeamspeakMessageAbstract {
    @Override
    public void execute(TeamspeakBotService bot, String... msg) {
        bot.sendChannelMessage(null, this);
    }

    @Override
    public String messageType() {
        return MessageType.DEFAULT;
    }
}
