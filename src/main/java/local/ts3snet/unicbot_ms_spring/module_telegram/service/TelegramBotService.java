package local.ts3snet.unicbot_ms_spring.module_telegram.service;

import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.Map;

public interface TelegramBotService extends LongPollingBot {
    void sendMessage(String chatId, String msg);
    void sendMessage(Long chatId, String msg);
    void sendMessageForAllSubscribers(String type, String msg);

    default void sendInlineKeyboard(Long chatId, String mainTitle, Map<String, String> titles) {
    }
    default void sendInlineKeyboardForAllSubscribers(String type) {
    }
    default void editMessageText(Long chatId, Integer messageId, String update) {
    }
}
