package local.ts3snet.unicbot_ms_spring.module_telegram.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.config.UniclogMyFitnessTelegramBotConfig;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.CommandLet;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service("uniclogMyFitnessTelegramBotServiceImpl")
public class UniclogMyFitnessTelegramBotServiceImpl extends TelegramLongPollingBot implements TelegramBotService {
    final UniclogMyFitnessTelegramBotConfig config;
    private final Map<String, UniclogMyFitnessMessageAbstract> messages;
    private final CommandLet commandLet;

    public UniclogMyFitnessTelegramBotServiceImpl(
            UniclogMyFitnessTelegramBotConfig config,
            @Qualifier("myFitnessMessageMap")
                    Map<String, UniclogMyFitnessMessageAbstract> messages,
            CommandLet commandLet) {
        this.config = config;
        this.messages = messages;
        this.commandLet = commandLet;

        log.info("UniclogMyFitnessTelegramBotServiceImpl init...");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text;
        if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getMessage();
            text = update.getCallbackQuery().getData();
        } else
            text = message.getText();
        log.info("Received: " + message);
        Integer messageId = message.getMessageId();
        String authorSignature = message.getFrom().getUserName();
        Long userId = message.getChatId();

        commandLet.update(text);
        UniclogMyFitnessMessageAbstract msg =
                messages.getOrDefault(commandLet.getCmd(), messages.get(MessageType.DEFAULT.getTextValue()));
        if (msg.serviceMessage() && !update.hasCallbackQuery())
            msg = messages.get(MessageType.DEFAULT.getTextValue());
        msg.setTextMessage(text);
        msg.setUserId(userId);
        msg.setMessageId(messageId);
        msg.setUserName(authorSignature);

        msg.execute(this, commandLet.getFirstParameter(), commandLet.getAllMessage());
    }

    @Override
    public void sendMessage(String chatId, String msg) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(msg);
        try {
            execute(message);
            log.info(message.toString());
        } catch (TelegramApiException e) {
            log.error("chatId=" + chatId + ": " + e.getMessage());
        }
    }

    @Override
    public void sendMessage(Long chatId, String msg) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(msg);
        try {
            execute(message);
            log.info(message.toString());
        } catch (TelegramApiException e) {
            log.error("chatId=" + chatId + ": " + e.getMessage());
        }
    }

    @Override
    public void sendMessageForAllSubscribers(String type, String msg) {
        messages.get(Objects.requireNonNullElseGet(type, MessageType.MESSAGE_FOR_ALL_SUBSCRIBERS::getTextValue))
                .execute(this, msg);
    }

    @Override
    public void sendInlineKeyboard(Long chatId, String mainTitle, Map<String, String> titlesForInlineButton) {
        SendMessage message = new SendMessage();
        message.setText(mainTitle);
        message.setChatId(chatId.toString());
        message.setReplyMarkup(inlineKeyboardBuilder(titlesForInlineButton));
        try {
            execute(message);
            log.info(message.toString());
        } catch (TelegramApiException e) {
            log.error("chatId=" + chatId + ": " + e.getMessage());
        }
    }

    @Override
    public void sendInlineKeyboardForAllSubscribers(String type) {
        messages.get(type).execute(this);
    }

    @Override
    public void editMessageText(Long chatId, Integer messageId, String update) {
        EditMessageText message = new EditMessageText();
        message.setChatId(chatId.toString());
        message.setMessageId(Math.toIntExact(messageId));
        message.setText(update);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup inlineKeyboardBuilder(Map<String, String> titles) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> inlineButtonList = new ArrayList<>();
        titles.forEach((key, title) -> {
            InlineKeyboardButton inlineButton = new InlineKeyboardButton();
            inlineButton.setText(title);
            inlineButton.setCallbackData(key);
            inlineButtonList.add(inlineButton);
        });
        rowList.add(inlineButtonList);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


    @Override
    public String getBotUsername() {
        return config.getTelegramBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
