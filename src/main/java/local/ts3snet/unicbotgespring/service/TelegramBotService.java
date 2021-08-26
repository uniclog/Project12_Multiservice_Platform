package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.config.TelegramBotConfig;
import local.ts3snet.unicbotgespring.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component("telegrambotservice")
public class TelegramBotService extends TelegramLongPollingBot {
    final TelegramBotConfig config;

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public TelegramBotService(TelegramBotConfig config) {
        this.config = config;
        log.info("TelegramBotService init...");
    }

    @Override
    public void onUpdateReceived(Update update) {
            log.info("Received: " + update.getMessage());

            String message = update.getMessage().getText();
            String authorSignature = update.getMessage().getAuthorSignature();
            Long userId = update.getMessage().getChatId();
            switch (message) {
                case "/sub": {
                    UserEntity user = new UserEntity();
                    user.setUserTelegramId(userId);
                    user.setSubscriber(true);
                    user.setUserName(authorSignature);

                    userService.save(user);
                    break;
                }
                case "/unsub": {
                    UserEntity user = new UserEntity();
                    user.setUserTelegramId(userId);
                    user.setSubscriber(false);
                    user.setUserName(authorSignature);

                    userService.update(user);
                    break;
                }
                default:
                    sendMessage(userId, "Привет ...");
            }
    }

    public void sendMessageForAllSubscribers(String msg) {
        userService.findAllSubscribers().forEach(e -> {
            sendMessage(e.getUserTelegramId(), msg);
        });
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
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public String getBotUsername() {
        return config.getBotUserName();
    }

    public String getBotToken() {
        return config.getToken();
    }
}
