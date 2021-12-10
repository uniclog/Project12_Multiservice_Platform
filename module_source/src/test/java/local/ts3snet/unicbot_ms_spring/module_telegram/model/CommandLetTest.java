package local.ts3snet.unicbot_ms_spring.module_telegram.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(classes = CommandLet.class)
class CommandLetTest {
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
        String testMsg = MessageType.NEXT;
        commandLet.update(testMsg);
        log.info("getCmd = " + commandLet.getCmd());
        assertEquals(MessageType.NEXT, commandLet.getCmd());
    }

    @Test
    void updateFirstParamTest() {
        String testMsg = MessageType.NEXT + " 5";
        commandLet.update(testMsg);
        log.info("getFirstParameter = " + commandLet.getFirstParameter());
        assertEquals(MessageType.NEXT, commandLet.getCmd());
        assertEquals("5", commandLet.getFirstParameter());
    }

    @Test
    void updateAllTextMessageTest() {
        String testMsg = MessageType.NEXT + " 5 test line";
        commandLet.update(testMsg);
        log.info("getAllMessage = " + commandLet.getAllMessage());
        assertEquals(MessageType.NEXT, commandLet.getCmd());
        assertEquals("5 test line", commandLet.getAllMessage());
    }

    @Test
    void updateForceTest() {
        String testMsg = " asdasd sds s " + MessageType.NEXT + " asdsd  sss daw^*&!@#(!@  5 test line";
        commandLet.update(testMsg);
        log.info("getAllMessage = " + commandLet.getAllMessage());
        assertEquals(MessageType.NEXT, commandLet.getCmd());
        assertEquals("asdsd  sss daw^*&!@#(!@  5 test line", commandLet.getAllMessage());
    }
}