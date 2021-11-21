package local.ts3snet.unicbot_ms_spring.module_telegram.config;

import local.ts3snet.unicbot_ms_spring.module_telegram.model.MessageInterface;
import local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages.UniclogMyFitnessMessageAbstract;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Configuration
//@PropertySource("classpath:application.properties")
@PropertySource("classpath:app.config.properties")
public class UniclogMyFitnessTelegramBotConfig {
    @Value("${telegram.bot.myfitness.name}")
    private String telegramBotName;

    @Value("${telegram.bot.myfitness.token}")
    private String token;

    @Bean
    @Qualifier("myFitnessMessageMap")
    public Map<String, UniclogMyFitnessMessageAbstract> myFitnessMessageBean(List<UniclogMyFitnessMessageAbstract> messages) {
        return messages.stream().collect(
                         Collectors.toMap(
                                 MessageInterface::messageType,
                                 Function.identity())
                 );
    }
}
