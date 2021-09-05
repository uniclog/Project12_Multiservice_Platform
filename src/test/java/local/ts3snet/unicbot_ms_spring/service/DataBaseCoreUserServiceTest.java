package local.ts3snet.unicbot_ms_spring.service;

import local.ts3snet.unicbot_ms_spring.core.entity.CoreUserEntity;
import local.ts3snet.unicbot_ms_spring.core.repository.CoreUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class DataBaseCoreUserServiceTest {
    @Autowired
    private CoreUserRepository coreUserRepository;

    @Test
    void contextTest() {
        assertNotNull(coreUserRepository);
    }

    @Test
    void getSubscribers() {
        CoreUserEntity user = new CoreUserEntity();
        user.setUserName("John");
        user.setSubscriber(true);
        user.setUserTelegramId((long)123456789);
        coreUserRepository.save(user);
        List<CoreUserEntity> subscribers = (List<CoreUserEntity>) coreUserRepository.findAllBySubscriber(true);
        log.debug(subscribers.toString());
        assertFalse(subscribers.isEmpty());

        user = new CoreUserEntity();
        user.setUserName("Den");
        user.setSubscriber(false);
        user.setUserTelegramId((long)987654321);
        coreUserRepository.save(user);
        subscribers = (List<CoreUserEntity>) coreUserRepository.findAllBySubscriber(false);
        log.debug(subscribers.toString());
        assertFalse(subscribers.isEmpty());
    }
    @Test
    void givenUserFromBD() {
        CoreUserEntity user = new CoreUserEntity();
        user.setUserName("John");
        user.setSubscriber(true);
        user.setUserTelegramId((long)123456789);
        coreUserRepository.save(user);
        List<CoreUserEntity> users = (List<CoreUserEntity>) coreUserRepository.findAll();
        assertFalse(users.isEmpty());

        Long telegramId = (long) 123456789;
        String name = "Test";
        CoreUserEntity user1 = coreUserRepository.findById(users.get(0).getId()).get();
        user1.setUserTelegramId(telegramId);
        user1.setUserName(name);
        coreUserRepository.save(user1);

        user = coreUserRepository.findById(user1.getId()).get();
        assertEquals(user.getUserTelegramId(), telegramId);
        assertEquals(user.getUserName(), name);
    }
    @Test
    void deleteUserFromDB() {
        CoreUserEntity user = new CoreUserEntity();
        user.setUserName("Delete");
        user.setSubscriber(true);
        user.setUserTelegramId((long)12211221);
        coreUserRepository.save(user);
        CoreUserEntity delUser = coreUserRepository.findByUserTelegramId(user.getUserTelegramId());
        coreUserRepository.deleteById(delUser.getId());
        assertNull(coreUserRepository.findByUserTelegramId(delUser.getUserTelegramId()));
    }
}