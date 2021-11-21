package local.ts3snet.unicbot_ms_spring.module_teamspeak.config;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.TeamspeakMessageInterface;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.TeamspeakMessageAbstract;
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
@PropertySource("classpath:application.properties")
public class TeamspeakBotConfig {
    @Value("${teamspeak.bot.login}")
    private String login;
    @Value("${teamspeak.bot.password}")
    private String password;
    @Value("${teamspeak.bot.server.ip_address}")
    private String ipAddress;
    @Value("${teamspeak.bot.server.port}")
    private int port;

    @Bean
    @Qualifier("ts3sMessageMap")
    public Map<String, TeamspeakMessageAbstract> ts3sMessageBean(List<TeamspeakMessageAbstract> messages) {
        return messages.stream().collect(
                Collectors.toMap(
                        TeamspeakMessageInterface::messageType,
                        Function.identity())
        );
    }
}
