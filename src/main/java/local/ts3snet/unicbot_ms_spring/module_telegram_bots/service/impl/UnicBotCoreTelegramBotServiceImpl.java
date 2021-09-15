package local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.config.UnicBotCoreTelegramBotConfig;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.impl.Default;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotCoreTelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Component("unicBotCoreTelegramBotServiceImpl")
public class UnicBotCoreTelegramBotServiceImpl extends TelegramLongPollingBot implements UnicBotCoreTelegramBotService {
    final UnicBotCoreTelegramBotConfig config;
    private final Map<String, UnicBotCoreMessageAbstract> messages;

    public UnicBotCoreTelegramBotServiceImpl(UnicBotCoreTelegramBotConfig config, List<UnicBotCoreMessageAbstract> messages) {
        this.config = config;
        this.messages = messages.stream().collect(toMap(UnicBotCoreMessageAbstract::messageType, Function.identity()));

        log.info("UnicBotCoreTelegramBotServiceImpl init...");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.info("Received: " + message);
        String text = message.getText();
        String authorSignature = message.getFrom().getUserName();
        Long userId = message.getChatId();

        UnicBotCoreMessageAbstract msg = messages.getOrDefault(text, new Default());
        msg.setTextMessage(text);
        msg.setUserId(userId);
        msg.setUserName(authorSignature);

        msg.execute(this);
    }

    @Override
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

    @Override
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
    public void sendMessageForAllSubscribers(String msg) {
        messages.get("messageForAllSubscribers").execute(this, msg);
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
