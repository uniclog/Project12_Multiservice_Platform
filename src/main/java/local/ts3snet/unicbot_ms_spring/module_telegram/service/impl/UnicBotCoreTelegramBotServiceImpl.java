package local.ts3snet.unicbot_ms_spring.module_telegram.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.config.UnicBotCoreTelegramBotConfig;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.CommandLet;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.UnicBotCoreMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.uniccore_messages.impl.Default;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Component("unicBotCoreTelegramBotServiceImpl")
public class UnicBotCoreTelegramBotServiceImpl extends TelegramLongPollingBot implements TelegramBotService {
    final UnicBotCoreTelegramBotConfig config;
    private final Map<String, UnicBotCoreMessageAbstract> messages;
    private final CommandLet commandLet;

    public UnicBotCoreTelegramBotServiceImpl(
            UnicBotCoreTelegramBotConfig config,
            List<UnicBotCoreMessageAbstract> messages,
            CommandLet commandLet) {
        this.config = config;
        this.messages = messages.stream().collect(toMap(UnicBotCoreMessageAbstract::messageType, Function.identity()));
        this.commandLet = commandLet;

        log.info("UnicBotCoreTelegramBotServiceImpl init...");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.info("Received: " + message);
        String text = message.getText();
        String authorSignature = message.getFrom().getUserName();
        Long userId = message.getChatId();

        commandLet.update(text);
        UnicBotCoreMessageAbstract msg =
                messages.getOrDefault(commandLet.getCmd(), new Default());
        msg.setTextMessage(text);
        msg.setUserId(userId);
        msg.setUserName(authorSignature);

        msg.execute(this, commandLet.getFirstParameter(), commandLet.getAllMessage());
    }

    @Override
    public void sendMessage(String chatId, String msg) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(msg);
        try {
            execute( message );
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
            execute( message );
            log.info(message.toString());
        } catch (TelegramApiException e) {
            log.error("chatId=" + chatId + ": " + e.getMessage());
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
