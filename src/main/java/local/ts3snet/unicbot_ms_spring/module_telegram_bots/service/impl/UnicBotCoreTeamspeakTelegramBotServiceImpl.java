package local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.service.UnicBotCoreTeamspeakTelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component("unicBotCoreTeamspeakTelegramBotServiceImpl")
public class UnicBotCoreTeamspeakTelegramBotServiceImpl extends TelegramLongPollingBot implements UnicBotCoreTeamspeakTelegramBotService {


    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void sendMessage(String chatId, String msg) {

    }

    @Override
    public void sendMessage(Long chatId, String msg) {

    }

    @Override
    public void sendMessageForAllSubscribers(String msg) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
