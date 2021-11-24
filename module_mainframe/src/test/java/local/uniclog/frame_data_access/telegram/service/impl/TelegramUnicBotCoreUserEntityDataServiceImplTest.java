package local.uniclog.frame_data_access.telegram.service.impl;

import local.uniclog.frame_data_access.telegram.entity.TelegramUnicBotCoreUserEntity;
import local.uniclog.frame_data_access.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {
        "spring.jpa.generate-ddl=true",
        "spring.jpa.hibernate.ddl-auto=create"
})
class TelegramUnicBotCoreUserEntityDataServiceImplTest {
    @Autowired
    private TelegramUnicBotCoreUserEntityDataService entityDataService;

    @Test
    void setTelegramUnicBotCoreRepository() {
        assertNotNull(entityDataService);
    }

    @ParameterizedTest
    @CsvSource({"123, Name1, true", "456, Name2, false"})
    void save(ArgumentsAccessor arguments) {
        TelegramUnicBotCoreUserEntity entity = new TelegramUnicBotCoreUserEntity();
        entity.setUserTelegramId(arguments.getLong(0));
        entity.setUserName(arguments.getString(1));
        entity.setSubscriber(arguments.getBoolean(2));
        entityDataService.save(entity);
        TelegramUnicBotCoreUserEntity newEntity = entityDataService.findByUserTelegramId(arguments.getLong(0));


        assertAll("User properties",
                () -> assertEquals(entity.getId(), newEntity.getId()),
                () -> assertEquals(entity.getUserName(), newEntity.getUserName()),
                () -> assertEquals(entity.getSubscriber(), newEntity.getSubscriber()),
                () -> assertEquals(entity, newEntity)
        );

        newEntity.setSubscriber(!arguments.getBoolean(2));
        entityDataService.save(newEntity);
        TelegramUnicBotCoreUserEntity newEntity2 = entityDataService.findByUserTelegramId(arguments.getLong(0));

        int count = entityDataService.findAll().size();
        assertAll("User properties",
                () -> assertEquals(newEntity.getId(), newEntity2.getId()),
                () -> assertEquals(newEntity.getUserName(), newEntity2.getUserName()),
                () -> assertNotEquals(entity.getSubscriber(), newEntity2.getSubscriber()),
                () -> assertNotEquals(entity, newEntity2),
                () -> assertEquals(1, count)
        );
    }

    @ParameterizedTest
    @CsvSource({"123, Name1, true, false", "456, Name2, false, true"})
    void update(ArgumentsAccessor arguments) {
        TelegramUnicBotCoreUserEntity entity = new TelegramUnicBotCoreUserEntity();
        entity.setUserTelegramId(arguments.getLong(0));
        entity.setUserName(arguments.getString(1));
        entity.setSubscriber(arguments.getBoolean(2));
        entityDataService.save(entity);
        TelegramUnicBotCoreUserEntity oldEntity = entityDataService.findByUserTelegramId(arguments.getLong(0));
        oldEntity.setSubscriber(arguments.getBoolean(3));
        entityDataService.update(oldEntity);
        TelegramUnicBotCoreUserEntity newEntity = entityDataService.findByUserTelegramId(arguments.getLong(0));
        assertAll("User properties",
                () -> assertEquals(entity.getId(), newEntity.getId()),
                () -> assertEquals(entity.getUserName(), newEntity.getUserName()),
                () -> assertNotEquals(entity.getSubscriber(), newEntity.getSubscriber())
        );
    }

    @Test
    void findByUserTelegramId() {
        Long id = 123123L;
        TelegramUnicBotCoreUserEntity entity = new TelegramUnicBotCoreUserEntity();
        entity.setUserTelegramId(id);
        entityDataService.save(entity);
        TelegramUnicBotCoreUserEntity newEntity = entityDataService.findByUserTelegramId(id);
        assertEquals(id, newEntity.getUserTelegramId());
    }

    @ParameterizedTest
    @CsvSource({
            "false, false, 0",
            "true, , 1",
            "true, false, 1",
            "true, true, 2"})
    void findAllSubscribers(ArgumentsAccessor arguments) {
        TelegramUnicBotCoreUserEntity entityTestSub1 = new TelegramUnicBotCoreUserEntity();
        entityTestSub1.setSubscriber(arguments.getBoolean(0));
        entityDataService.save(entityTestSub1);
        TelegramUnicBotCoreUserEntity entityTestSub2 = new TelegramUnicBotCoreUserEntity();
        entityTestSub2.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entityTestSub2);
        int subCount = entityDataService.findAllSubscribers().size();
        assertEquals(arguments.getInteger(2), subCount);
    }

    @Test
    void findAll() {
        TelegramUnicBotCoreUserEntity entityTestSub1 = new TelegramUnicBotCoreUserEntity();
        entityTestSub1.setUserTelegramId(123L);
        entityDataService.save(entityTestSub1);
        assertEquals(1, entityDataService.findAll().size());
        TelegramUnicBotCoreUserEntity entityTestSub2 = new TelegramUnicBotCoreUserEntity();
        entityTestSub2.setUserTelegramId(456L);
        entityDataService.save(entityTestSub2);
        assertEquals(2, entityDataService.findAll().size());
    }

    @Test
    void deleteAllByUserTelegramId() {
        TelegramUnicBotCoreUserEntity user = new TelegramUnicBotCoreUserEntity();
        user.setUserTelegramId(123L);
        entityDataService.save(user);
        user = new TelegramUnicBotCoreUserEntity();
        user.setUserTelegramId(123L);
        entityDataService.save(user);
        TelegramUnicBotCoreUserEntity delUser = entityDataService.findByUserTelegramId(user.getUserTelegramId());
        List<TelegramUnicBotCoreUserEntity> deleted = entityDataService.deleteAllByUserTelegramId(delUser.getUserTelegramId());
        assertAll("Test case",
                () -> assertNotNull(deleted),
                () -> assertEquals(2, deleted.size())
        );
    }
}