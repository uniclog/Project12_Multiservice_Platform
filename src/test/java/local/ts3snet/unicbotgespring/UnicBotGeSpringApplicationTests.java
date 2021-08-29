package local.ts3snet.unicbotgespring;

import local.ts3snet.unicbotgespring.api.ApiTelegramBotInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UnicBotGeSpringApplicationTests {
    @Autowired
    private ApiTelegramBotInitializer bot;

    @Test
    void contextLoads() {
        assertNotNull(bot);
    }

}
