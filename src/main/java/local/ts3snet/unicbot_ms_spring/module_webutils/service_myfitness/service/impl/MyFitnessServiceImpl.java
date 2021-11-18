package local.ts3snet.unicbot_ms_spring.module_webutils.service_myfitness.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_myfitness.model.MyFitnessMessagesGenerator;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_myfitness.service.MyFitnessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MyFitnessServiceImpl implements MyFitnessService {
    private TelegramBotService botService;

    @Autowired
    public void setUnicBotTORGTelegramBotService(@Qualifier("uniclogMyFitnessTelegramBotServiceImpl") TelegramBotService bot) {
        this.botService = bot;
    }

    private MyFitnessMessagesGenerator messagesGenerator;

    @Autowired
    public void setUnicBotTORGTelegramBotService(MyFitnessMessagesGenerator messagesGenerator) {
        this.messagesGenerator = messagesGenerator;
    }

    @Scheduled(cron = "0 0 10-22 * * *")
    public void everyHourNotify() {
        botService.sendMessageForAllSubscribers(null,
                messagesGenerator.generate(
                        List.of(
                                "Воду пьем! хорошо живем!",
                                "Время воды!",
                                "А вы, уже пили воду?!",
                                "Пора пить воду")));
        botService.sendInlineKeyboardForAllSubscribers(MessageType.INLINE_BUTTON_ACCEPT_SOURCE.getTextValue());
    }

    @Scheduled(cron = "0 15 22 * * *")
    public void everyDayNotifyClearDay() {
        botService.sendMessageForAllSubscribers(MessageType.STATISTIC_CLEAR.getTextValue(), null);
    }

    @Scheduled(cron = "0 0 17 * * *")
    public void everyFitnessNotify() {
        botService.sendMessageForAllSubscribers(null,
                messagesGenerator.generate(
                        List.of(
                                "Пора качать попу!",
                                "Пора заниматься!")));
    }
}
