package local.ts3snet.unicbot_ms_spring.module_telegram_bots.service;

import org.telegram.telegrambots.meta.generics.LongPollingBot;

public interface UnicBotCoreTeamspeakTelegramBotService extends LongPollingBot {
    void sendMessage(String chatId, String msg);
    void sendMessage(Long chatId, String msg);
    void sendMessageForAllSubscribers(String msg);
}