package local.ts3snet.unicbot_ms_spring.module_teamspeak.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
}
