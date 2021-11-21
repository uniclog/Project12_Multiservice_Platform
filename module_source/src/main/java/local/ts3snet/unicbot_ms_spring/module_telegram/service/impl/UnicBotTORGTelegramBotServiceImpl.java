package local.ts3snet.unicbot_ms_spring.module_telegram.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.config.UnicBotTORGTelegramBotConfig;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.UnicBotTORGMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.torg_messages.impl.Default;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Component("unicBotTORGTelegramBotServiceImpl")
public class UnicBotTORGTelegramBotServiceImpl extends TelegramLongPollingBot implements TelegramBotService {
    final UnicBotTORGTelegramBotConfig config;
    private final Map<String, UnicBotTORGMessageAbstract> messages;

    public UnicBotTORGTelegramBotServiceImpl(
            UnicBotTORGTelegramBotConfig config,
            @Qualifier("torgMessageMap")
            Map<String, UnicBotTORGMessageAbstract> messages) {
        this.config = config;
        this.messages = messages;
                //messages.stream().collect(toMap(UnicBotTORGMessageAbstract::messageType, Function.identity()));
    }

    @PostConstruct
    private void init() {
        log.info("UnicBotTORGTelegramBotService init...");
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
    }

    @Override
    public void sendMessageForAllSubscribers(String type, String msg) {
        messages.get(Objects.requireNonNullElse(type, MessageType.MESSAGE_FOR_ALL_SUBSCRIBERS)).execute(this, msg);
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

    @Override
    public String getBotUsername() {
        return config.getTelegramBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
