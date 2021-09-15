package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:client.keystore.properties")
public class KeyModuleWebConfig {
    @Value("${web.chat.url}")
    private String url;
    @Value("${web.chat.update}")
    private Boolean updateLoop;
    @Value("${web.chat.key.pattern}")
    private String keyPattern;
    @Value("${web.chat.update.rate}")
    private String updateRate;
}
