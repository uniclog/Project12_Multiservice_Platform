package local.ts3snet.unicbot_ms_spring.module_telegram.service;

import org.telegram.telegrambots.meta.generics.LongPollingBot;

public interface TelegramBotService extends LongPollingBot {
    void sendMessage(String chatId, String msg);
    void sendMessage(Long chatId, String msg);
    void sendMessageForAllSubscribers(String msg);
}