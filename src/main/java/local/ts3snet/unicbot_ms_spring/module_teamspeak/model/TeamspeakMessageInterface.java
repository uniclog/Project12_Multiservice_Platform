package local.ts3snet.unicbot_ms_spring.module_teamspeak.model;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.TeamspeakBotService;

public interface TeamspeakMessageInterface {
    void execute(TeamspeakBotService bot, String... msg);
    String messageType();

    String getMessageText();
}