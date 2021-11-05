package local.ts3snet.unicbot_ms_spring.module_teamspeak.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(classes = CommandLet.class)
class TeamspeakCommandLetTest {
    @Autowired
    private CommandLet commandLet;

    @Test
    void updateEntryTest() {
        String testMsg = "";
        commandLet.update(testMsg);
        log.info("getCmd = " + commandLet.getCmd());
        assertEquals(MessageType.DEFAULT, commandLet.getCmd());
    }

    @Test
    void updateTest() {
        String testMsg = MessageType.NEXT.getTextValue();
        commandLet.update(testMsg);
        log.info("getCmd = " + commandLet.getCmd());
        assertEquals(MessageType.NEXT, commandLet.getCmd());
    }

}