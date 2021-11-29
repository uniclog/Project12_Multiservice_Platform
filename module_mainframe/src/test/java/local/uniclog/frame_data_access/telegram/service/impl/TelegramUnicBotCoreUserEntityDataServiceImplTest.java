package local.uniclog.frame_data_access.telegram.service.impl;

import local.uniclog.frame_data_access.DataServiceTestConfiguration;
import local.uniclog.frame_data_access.telegram.entity.TelegramUnicBotCoreUserEntity;
import local.uniclog.frame_data_access.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@ContextConfiguration(classes = DataServiceTestConfiguration.class)
class TelegramUnicBotCoreUserEntityDataServiceImplTest {
    @Autowired
    @Qualifier("beanTelegramUnicBotCoreUserEntityDataServiceTest")
    private TelegramUnicBotCoreUserEntityDataService entityDataService;

    private TelegramUnicBotCoreUserEntity entity;

    private final Long telegramId = 123L;
    private final String userName = "Name";
    private final Boolean subscriber = true;

    @BeforeEach
    void setUp() {
        entity = new TelegramUnicBotCoreUserEntity();
        entity.setUserTelegramId(telegramId);
        entity.setUserName(userName);
        entity.setSubscriber(subscriber);
        entityDataService.save(entity);
    }

    @Test
    void setTelegramUnicBotCoreRepository() {
        assertNotNull(entityDataService);
        assertNotNull(entityDataService.findByUserTelegramId(telegramId));
    }

    @ParameterizedTest
    @CsvSource({"123, Name1, true", "456, Name2, false"})
    void save(ArgumentsAccessor arguments) {
        assertEquals(entity, entityDataService.findByUserTelegramId(telegramId));
        TelegramUnicBotCoreUserEntity temp = new TelegramUnicBotCoreUserEntity();
        temp.setUserTelegramId(arguments.getLong(0));
        temp.setUserName(arguments.getString(1));
        temp.setSubscriber(arguments.getBoolean(2));
        entityDataService.save(temp);
        TelegramUnicBotCoreUserEntity check = entityDataService.findByUserTelegramId(arguments.getLong(0));

        assertAll("User properties",
                () -> assertEquals(temp, check),
                () -> {
                    if (Objects.equals(arguments.getLong(0), telegramId))
                        assertEquals(1, entityDataService.findAll().size());
                    else assertEquals(2, entityDataService.findAll().size());
                }
        );
    }

    @Test
    void update() {
        assertEquals(entity, entityDataService.findByUserTelegramId(telegramId));

        TelegramUnicBotCoreUserEntity oldEntity = entityDataService.findByUserTelegramId(telegramId);
        oldEntity.setSubscriber(!subscriber);
        entityDataService.update(oldEntity);
        TelegramUnicBotCoreUserEntity newEntity = entityDataService.findByUserTelegramId(telegramId);
        assertAll("User properties",
                () -> assertEquals(entity.getId(), newEntity.getId()),
                () -> assertEquals(userName, newEntity.getUserName()),
                () -> assertNotEquals(subscriber, newEntity.getSubscriber())
        );

        TelegramUnicBotCoreUserEntity temp = new TelegramUnicBotCoreUserEntity();
        temp.setUserTelegramId(1234567890L);
        entityDataService.update(temp);
        assertNotNull(entityDataService.findByUserTelegramId(1234567890L));
    }

    @Test
    void findByUserTelegramId() {
        TelegramUnicBotCoreUserEntity newEntity = entityDataService.findByUserTelegramId(telegramId);
        assertEquals(entity, newEntity);
        newEntity = entityDataService.findByUserTelegramId(telegramId + 1);
        assertNull(newEntity);
    }

    @ParameterizedTest
    @CsvSource({
            "123, false, 456, false, 0",
            "123, true, , , 1",
            "123, true, 456, false, 1",
            "123, true, 456, true, 2"})
    void findAllSubscribers(ArgumentsAccessor arguments) {
        assertEquals(1, entityDataService.findAllSubscribers().size());

        TelegramUnicBotCoreUserEntity entityTestSub1 = new TelegramUnicBotCoreUserEntity();
        entityTestSub1.setUserTelegramId(arguments.getLong(0));
        entityTestSub1.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entityTestSub1);
        TelegramUnicBotCoreUserEntity entityTestSub2 = new TelegramUnicBotCoreUserEntity();
        entityTestSub2.setUserTelegramId(arguments.getLong(2));
        entityTestSub2.setSubscriber(arguments.getBoolean(3));
        entityDataService.save(entityTestSub2);
        assertEquals(arguments.getInteger(4), entityDataService.findAllSubscribers().size());
    }

    @Test
    void findAll() {
        assertEquals(1, entityDataService.findAll().size());
        TelegramUnicBotCoreUserEntity entityTestSub2 = new TelegramUnicBotCoreUserEntity();
        entityTestSub2.setUserTelegramId(456L);
        entityDataService.save(entityTestSub2);
        assertEquals(2, entityDataService.findAll().size());
    }

    @Test
    void deleteAllByUserTelegramId() {
        TelegramUnicBotCoreUserEntity user = new TelegramUnicBotCoreUserEntity();
        user.setUserTelegramId(456L);
        entityDataService.save(user);
        TelegramUnicBotCoreUserEntity delUser = entityDataService.findByUserTelegramId(user.getUserTelegramId());
        List<TelegramUnicBotCoreUserEntity> deleted = entityDataService.deleteAllByUserTelegramId(delUser.getUserTelegramId());
        List<TelegramUnicBotCoreUserEntity> notFoundEntity = entityDataService.deleteAllByUserTelegramId(delUser.getUserTelegramId());
        assertAll("Test case",
                () -> assertNotNull(deleted),
                () -> assertEquals(1, deleted.size()),
                () -> assertNull(notFoundEntity)
        );
    }
}