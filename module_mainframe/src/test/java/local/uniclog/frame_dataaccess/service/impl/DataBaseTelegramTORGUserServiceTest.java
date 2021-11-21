package local.uniclog.frame_dataaccess.service.impl;

import local.uniclog.frame_dataaccess.entity.TelegramTORGUserEntity;
import local.uniclog.frame_dataaccess.repository.TelegramTORGUserRepository;
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
        List<TelegramTORGUserEntity> subscribers = telegramTORGUserRepository.findAllBySubscriber(true);
        log.debug(subscribers.toString());
        assertFalse(subscribers.isEmpty());

        user = new TelegramTORGUserEntity();
        user.setUserName("Den");
        user.setSubscriber(false);
        user.setUserTelegramId((long)987654321);
        telegramTORGUserRepository.save(user);
        subscribers = telegramTORGUserRepository.findAllBySubscriber(false);
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
        List<TelegramTORGUserEntity> users = telegramTORGUserRepository.findAll();
        assertFalse(users.isEmpty());

        Long telegramId = (long) 123456789;
        String name = "Test";
        TelegramTORGUserEntity user1 = telegramTORGUserRepository.findById(users.get(0).getId()).orElse(null);
        assertNotNull(user1);
        user1.setUserTelegramId(telegramId);
        user1.setUserName(name);
        telegramTORGUserRepository.save(user1);

        user = telegramTORGUserRepository.findById(user1.getId()).orElse(null);
        assertNotNull(user);
        final TelegramTORGUserEntity assertUser = user;
        assertAll("User properties",
                () -> assertEquals(assertUser.getUserTelegramId(), telegramId),
                () -> assertEquals(assertUser.getUserName(), name)
        );
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