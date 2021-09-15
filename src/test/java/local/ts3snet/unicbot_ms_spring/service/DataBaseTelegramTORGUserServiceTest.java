package local.ts3snet.unicbot_ms_spring.service;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramTORGUserEntity;
import local.ts3snet.unicbot_ms_spring.core.repository.TelegramTORGUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class DataBaseTelegramTORGUserServiceTest {
    @Autowired
    private TelegramTORGUserRepository telegramTORGUserRepository;

    @Test
    void contextTest() {
        assertNotNull(telegramTORGUserRepository);
    }

    @Test
    void getSubscribers() {
        TelegramTORGUserEntity user = new TelegramTORGUserEntity();
        user.setUserName("John");
        user.setSubscriber(true);
        user.setUserTelegramId((long)123456789);
        telegramTORGUserRepository.save(user);
        List<TelegramTORGUserEntity> subscribers = (List<TelegramTORGUserEntity>) telegramTORGUserRepository.findAllBySubscriber(true);
        log.debug(subscribers.toString());
        assertFalse(subscribers.isEmpty());

        user = new TelegramTORGUserEntity();
        user.setUserName("Den");
        user.setSubscriber(false);
        user.setUserTelegramId((long)987654321);
        telegramTORGUserRepository.save(user);
        subscribers = (List<TelegramTORGUserEntity>) telegramTORGUserRepository.findAllBySubscriber(false);
        log.debug(subscribers.toString());
        assertFalse(subscribers.isEmpty());
    }
    @Test
    void givenUserFromBD() {
        TelegramTORGUserEntity user = new TelegramTORGUserEntity();
        user.setUserName("John");
        user.setSubscriber(true);
        user.setUserTelegramId((long)123456789);
        telegramTORGUserRepository.save(user);
        List<TelegramTORGUserEntity> users = (List<TelegramTORGUserEntity>) telegramTORGUserRepository.findAll();
        assertFalse(users.isEmpty());

        Long telegramId = (long) 123456789;
        String name = "Test";
        TelegramTORGUserEntity user1 = telegramTORGUserRepository.findById(users.get(0).getId()).get();
        user1.setUserTelegramId(telegramId);
        user1.setUserName(name);
        telegramTORGUserRepository.save(user1);

        user = telegramTORGUserRepository.findById(user1.getId()).get();
        assertEquals(user.getUserTelegramId(), telegramId);
        assertEquals(user.getUserName(), name);
    }
    @Test
    void deleteUserFromDB() {
        TelegramTORGUserEntity user = new TelegramTORGUserEntity();
        user.setUserName("Delete");
        user.setSubscriber(true);
        user.setUserTelegramId((long)12211221);
        telegramTORGUserRepository.save(user);
        TelegramTORGUserEntity delUser = telegramTORGUserRepository.findByUserTelegramId(user.getUserTelegramId());
        telegramTORGUserRepository.deleteById(delUser.getId());
        assertNull(telegramTORGUserRepository.findByUserTelegramId(delUser.getUserTelegramId()));
    }
}