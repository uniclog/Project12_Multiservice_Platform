package local.ts3snet.unicbot_ms_spring.module_webutils.service_myfitness.service.impl;

import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_myfitness.service.MyFitnessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyFitnessServiceImpl implements MyFitnessService {
    private TelegramBotService botService;
    @Autowired
    public void setUnicBotTORGTelegramBotService(@Qualifier("uniclogMyFitnessTelegramBotServiceImpl") TelegramBotService bot) {
        this.botService = bot;
    }

    @Scheduled(cron = "0 0 10-22 * * *")
    public void everyHourNotify() {
        botService.sendMessageForAllSubscribers("Воду пьем! хорошо живем!");
    }

    @Scheduled(cron = "0 0 17 * * *")
    public void everyFitnessNotify() {
        botService.sendMessageForAllSubscribers("Пора качать попу!");
    }
}
