package local.ts3snet.unicbot_ms_spring.module_telegram.model;

import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;

public interface MessageInterface {
    /**
     * Business logic
     * @param bot bot interface
     * @param msg message for sending
     */
    void execute(TelegramBotService bot, String... msg);

    /**
     * Get message type
     * @return string
     */
    String messageType();

    /**
     * Is a service message
     * @return default false
     */
    default boolean serviceMessage() {
        return false;
    }
}