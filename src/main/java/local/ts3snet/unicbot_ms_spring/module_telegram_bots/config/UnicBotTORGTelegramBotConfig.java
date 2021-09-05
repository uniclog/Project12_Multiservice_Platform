package local.ts3snet.unicbot_ms_spring.module_telegram_bots.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class UnicBotTORGTelegramBotConfig {
    @Value("${telegram.bot.name}")
    private String telegramBotName;

    @Value("${telegram.bot.token}")
    private String token;
}