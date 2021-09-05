package local.ts3snet.unicbotgespring.service;

import org.telegram.telegrambots.meta.generics.LongPollingBot;

public interface TelegramBotService extends LongPollingBot {
    void sendMessage(String chatId, String msg);
    void sendMessage(Long chatId, String msg);
    void sendMessageForAllSubscribers(String msg);
}
