package local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.TeamspeakMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component(value = "ts3sMessageDefault")
public class Default extends TeamspeakMessageAbstract {
    @Override
    public void execute(TeamspeakMessageSender bot, String... msg) {
        bot.sendChannelMessage(null, "Привет ...");
    }

    @Override
    public String messageType() {
        return MessageType.DEFAULT.getTextValue();
    }
}
