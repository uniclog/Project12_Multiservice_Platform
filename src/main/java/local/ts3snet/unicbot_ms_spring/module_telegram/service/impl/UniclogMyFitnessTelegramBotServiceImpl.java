package local.ts3snet.unicbot_ms_spring.module_telegram.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.config.UniclogMyFitnessTelegramBotConfig;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.CommandLet;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl.Default;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

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
        log.info("Received: " + message);
        String text = message.getText();
        String authorSignature = message.getFrom().getUserName();
        Long userId = message.getChatId();

        commandLet.update(text);
        UniclogMyFitnessMessageAbstract msg =
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