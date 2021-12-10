package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.impl;


import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.MessageType;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_telegram.service.TelegramBotService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component(value = "myFitnessMessageClearStatistic")
public class StatisticClear extends UniclogMyFitnessMessageAbstract {
    /**
     * Send message for all users
     *
     * @param bot bot signature
     * @param msg user messages
     */
    @Override
    public void execute(TelegramBotService bot, String... msg) {
        getTelegramMyFitnessUserService().findAllSubscribers().forEach(e -> {
                    int waterCount = e.getWaterCount();
                    e.setWaterCount(0);
                    this.getTelegramMyFitnessUserService().update(e);

                    bot.sendMessage(e.getUserTelegramId(),
                            "Привет.\n" +
                                    "Сегодня вы пили воду " + waterCount + " "
                                    + (waterCount < 2 || waterCount > 4 ? "раз\n": "раза\n")
                                    + (waterCount > 5 ?
                                    "Молодец! Так держать." :
                                    waterCount > 3 ?
                                    "Хорошо! Так держать. еше немного и суточная норма"
                                            : "Ну что-то неважные результаты."));

                }
        );

        printDebugLog();
    }

    @Override
    public boolean serviceMessage() {
        return true;
    }

    @Override
    public String messageType() {
        return MessageType.STATISTIC_CLEAR.getTextValue();
    }
}
