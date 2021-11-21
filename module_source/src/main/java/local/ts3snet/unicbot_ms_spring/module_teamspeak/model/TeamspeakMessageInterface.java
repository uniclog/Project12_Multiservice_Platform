package local.ts3snet.unicbot_ms_spring.module_teamspeak.model;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;

public interface TeamspeakMessageInterface {
    void execute(TeamspeakMessageSender bot, String... msg);
    String messageType();

    String getMessageText();
}