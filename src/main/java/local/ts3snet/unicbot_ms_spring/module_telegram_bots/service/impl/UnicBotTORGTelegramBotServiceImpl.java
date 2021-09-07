package local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotTORGTelegramBotService;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.config.UnicBotTORGTelegramBotConfig;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.messages.impl.Default;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;


@Slf4j
@Component("UnicBotTORGTelegramBotServiceImpl")
public class UnicBotTORGTelegramBotServiceImpl extends TelegramLongPollingBot implements UnicBotTORGTelegramBotService {
    final UnicBotTORGTelegramBotConfig config;
    private final Map<String, UnicBotTORGMessageAbstract> messages;

    public UnicBotTORGTelegramBotServiceImpl(UnicBotTORGTelegramBotConfig config, List<UnicBotTORGMessageAbstract> messages) {
        this.config = config;
        this.messages = messages.stream().collect(toMap(UnicBotTORGMessageAbstract::messageType, Function.identity()));

        log.info("TelegramBotService init...");
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.info("Received: " + message);
        String text = message.getText();
        String authorSignature = message.getFrom().getUserName();
        Long userId = message.getChatId();

        UnicBotTORGMessageAbstract msg = messages.getOrDefault(text, new Default());
        msg.setTextMessage(text);
        msg.setUserId(userId);
        msg.setUserName(authorSignature);

        msg.execute(this);

        /*switch (text) {
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
        }*/
    }

    public void sendMessageForAllSubscribers(String text) {
        messages.get("messageForAllSubscribers").execute(this, text);
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
