package local.ts3snet.unicbot_ms_spring.module_telegram_bots.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
//@PropertySource("classpath:application.properties")
@PropertySource("classpath:client.keystore.properties")
public class UnicBotCoreTelegramBotConfig {
    @Value("${telegram.bot.uniccore.name}")
    private String telegramBotName;

    @Value("${telegram.bot.uniccore.token}")
    private String token;
}
