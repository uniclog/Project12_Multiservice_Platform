package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.entity.UserEntity;
import local.ts3snet.unicbotgespring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class DataBaseUserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextTest() {
        assertNotNull(userRepository);
    }

    @Test
    void getSubscribers() {
        UserEntity user = new UserEntity();
        user.setUserName("John");
        user.setSubscriber(true);
        user.setUserTelegramId((long)123456789);
        userRepository.save(user);
        List<UserEntity> subscribers = (List<UserEntity>) userRepository.findAllBySubscriber(true);
        log.debug(subscribers.toString());
        assertFalse(subscribers.isEmpty());

        user = new UserEntity();
        user.setUserName("Den");
        user.setSubscriber(false);
        user.setUserTelegramId((long)987654321);
        userRepository.save(user);
        subscribers = (List<UserEntity>) userRepository.findAllBySubscriber(false);
        log.debug(subscribers.toString());
        assertFalse(subscribers.isEmpty());
    }
    @Test
    void givenUserFromBD() {
        UserEntity user = new UserEntity();
        user.setUserName("John");
        user.setSubscriber(true);
        user.setUserTelegramId((long)123456789);
        userRepository.save(user);
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        assertFalse(users.isEmpty());

        Long telegramId = (long) 123456789;
        String name = "Test";
        UserEntity user1 = userRepository.findById(users.get(0).getId()).get();
        user1.setUserTelegramId(telegramId);
        user1.setUserName(name);
        userRepository.save(user1);

        user = userRepository.findById(user1.getId()).get();
        assertEquals(user.getUserTelegramId(), telegramId);
        assertEquals(user.getUserName(), name);
    }
    @Test
    void deleteUserFromDB() {
        UserEntity user = new UserEntity();
        user.setUserName("Delete");
        user.setSubscriber(true);
        user.setUserTelegramId((long)12211221);
        userRepository.save(user);
        UserEntity delUser = userRepository.findByUserTelegramId(user.getUserTelegramId());
        userRepository.deleteById(delUser.getId());
        assertNull(userRepository.findByUserTelegramId(delUser.getUserTelegramId()));
    }
}