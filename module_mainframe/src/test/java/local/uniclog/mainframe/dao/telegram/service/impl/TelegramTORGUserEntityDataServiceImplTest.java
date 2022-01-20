package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.DataServiceTestConfiguration;
import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.entity.TelegramTORGUserEntity;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataService;
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
class TelegramTORGUserEntityDataServiceImplTest {
    private final Long telegramId = 123L;
    private final String userName = "Name";
    private final Boolean subscriber = true;
    @Autowired
    @Qualifier("beanTelegramTORGUserEntityDataServiceTest")
    private TelegramTORGUserEntityDataService entityDataService;
    private TelegramTORGUserEntity entity;

    @BeforeEach
    void setUp() {
        entity = new TelegramTORGUserEntity();
        entity.setUserTelegramId(telegramId);
        entity.setUserName(userName);
        entity.setSubscriber(subscriber);
        entityDataService.save(entity);
    }

    @Test
    void setTelegramTORGUserRepository() {
        assertNotNull(entityDataService);
        assertNotNull(entityDataService.findByUserTelegramId(telegramId));
    }

    @ParameterizedTest
    @CsvSource({"123, Name1, true", "456, Name2, false"})
    void save(ArgumentsAccessor arguments) {
        assertEquals(entity, entityDataService.findByUserTelegramId(telegramId));
        TelegramTORGUserEntity temp = new TelegramTORGUserEntity();
        temp.setUserTelegramId(arguments.getLong(0));
        temp.setUserName(arguments.getString(1));
        temp.setSubscriber(arguments.getBoolean(2));
        entityDataService.save(temp);
        TelegramTORGUserEntity check = entityDataService.findByUserTelegramId(arguments.getLong(0));

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

        TelegramTORGUserEntity oldEntity = entityDataService.findByUserTelegramId(telegramId);
        oldEntity.setSubscriber(!subscriber);
        entityDataService.update(oldEntity);
        TelegramTORGUserEntity newEntity = entityDataService.findByUserTelegramId(telegramId);
        assertAll("User properties",
                () -> assertEquals(entity.getId(), newEntity.getId()),
                () -> assertEquals(userName, newEntity.getUserName()),
                () -> assertNotEquals(subscriber, newEntity.getSubscriber())
        );

        TelegramTORGUserEntity temp = new TelegramTORGUserEntity();
        temp.setUserTelegramId(1234567890L);
        entityDataService.update(temp);
        assertNotNull(entityDataService.findByUserTelegramId(1234567890L));
    }

    @Test
    void findAll() {
        assertEquals(1, entityDataService.findAll().size());
        TelegramTORGUserEntity entityTestSub2 = new TelegramTORGUserEntity();
        entityTestSub2.setUserTelegramId(456L);
        entityDataService.save(entityTestSub2);
        assertEquals(2, entityDataService.findAll().size());
    }

    @Test
    void findByUserTelegramId() {
        TelegramTORGUserEntity newEntity = entityDataService.findByUserTelegramId(telegramId);
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

        TelegramTORGUserEntity entityTestSub1 = new TelegramTORGUserEntity();
        entityTestSub1.setUserTelegramId(arguments.getLong(0));
        entityTestSub1.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entityTestSub1);
        TelegramTORGUserEntity entityTestSub2 = new TelegramTORGUserEntity();
        entityTestSub2.setUserTelegramId(arguments.getLong(2));
        entityTestSub2.setSubscriber(arguments.getBoolean(3));
        entityDataService.save(entityTestSub2);
        assertEquals(arguments.getInteger(4), entityDataService.findAllSubscribers().size());
    }

    @Test
    void deleteAllByUserTelegramId() {
        TelegramTORGUserEntity user = new TelegramTORGUserEntity();
        user.setUserTelegramId(456L);
        entityDataService.save(user);
        TelegramTORGUserEntity delUser = entityDataService.findByUserTelegramId(user.getUserTelegramId());
        List<TelegramTORGUserEntity> deleted = entityDataService.deleteAllByUserTelegramId(delUser.getUserTelegramId());
        List<TelegramTORGUserEntity> notFoundEntity = entityDataService.deleteAllByUserTelegramId(delUser.getUserTelegramId());
        assertAll("Test case",
                () -> assertNotNull(deleted),
                () -> assertEquals(1, deleted.size()),
                () -> assertTrue(notFoundEntity.isEmpty())
        );
    }

    @Test
    void convertToDataTransferObject() {
        TelegramTORGUserEntityDataTransferObject dto = entityDataService.convertToDataTransferObject(entity);
        TelegramTORGUserEntity entityFromDto = entityDataService.convertFromDataTransferObject(dto);

        assertEquals(entity, entityFromDto);
    }

    @Test
    void convertFromDataTransferObject() {
        TelegramTORGUserEntity temp = new TelegramTORGUserEntity();
        temp.setUserTelegramId(123123123L);
        temp.setSubscriber(true);

        TelegramTORGUserEntityDataTransferObject dto = entityDataService.convertToDataTransferObject(temp);
        TelegramTORGUserEntity entityFromDto = entityDataService.convertFromDataTransferObject(dto);
        assertEquals(temp, entityFromDto);
    }
}