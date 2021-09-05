package local.ts3snet.unicbot_ms_spring;

import local.ts3snet.unicbot_ms_spring.module_telegram_bots.api.ApiUnicBotTORGTelegramBotInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UnicBotGeSpringApplicationTests {
    @Autowired
    private ApiUnicBotTORGTelegramBotInitializer bot;

    @Test
    void contextLoads() {
        assertNotNull(bot);
    }

}
