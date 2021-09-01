package local.ts3snet.unicbotgespring.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class WebConfig {
    @Value("${web.chat.url}")
    private String url;
    @Value("${web.chat.update}")
    private Boolean updateLoop;
    @Value("${web.chat.key.pattern}")
    private String keyPattern;
}
