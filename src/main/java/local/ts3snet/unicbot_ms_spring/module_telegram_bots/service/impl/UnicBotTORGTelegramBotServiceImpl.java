package local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import local.ts3snet.unicbot_ms_spring.core.entity.CoreUserEntity;
import local.ts3snet.unicbot_ms_spring.core.service.CoreUserService;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.config.UnicBotTORGTelegramBotConfig;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;

@Slf4j
@Component("UnicBotTORGTelegramBotServiceImpl")
public class UnicBotTORGTelegramBotServiceImpl extends TelegramLongPollingBot implements UnicBotTORGTelegramBotService {
    final UnicBotTORGTelegramBotConfig config;

    private CoreUserService coreUserService;
    @Autowired
    public void setCoreUserService(CoreUserService coreUserService) {
        this.coreUserService = coreUserService;
    }


    public UnicBotTORGTelegramBotServiceImpl(UnicBotTORGTelegramBotConfig config) {
        this.config = config;
        log.info("TelegramBotService init...");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.info("Received: " + message);
        String text = message.getText();
        String authorSignature = message.getForwardSenderName();
        Long userId = message.getChatId();
        switch (text) {
            case "/sub": {
                CoreUserEntity user = new CoreUserEntity();
                user.setUserTelegramId(userId);
                user.setSubscriber(true);
                user.setUserName(authorSignature);

                coreUserService.save(user);
                break;
            }
            case "/unsub": {
                CoreUserEntity user = new CoreUserEntity();
                user.setUserTelegramId(userId);
                user.setSubscriber(false);
                user.setUserName(authorSignature);

                coreUserService.update(user);
                break;
            }
            default:
                sendMessage(userId, "Привет ...");
        }
    }

    public void sendMessageForAllSubscribers(String msg) {
        coreUserService.findAllSubscribers().forEach(e ->
            sendMessage(e.getUserTelegramId(), msg)
        );
    }

    public void sendMessage(String chatId, String msg) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(msg);
            execute( message );
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
    public void sendMessage(Long chatId, String msg) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText(msg);
            execute( message );
            log.info(message.toString());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public String getBotUsername() {
        return config.getTelegramBotName();
    }

    public String getBotToken() {
        return config.getToken();
    }
}
