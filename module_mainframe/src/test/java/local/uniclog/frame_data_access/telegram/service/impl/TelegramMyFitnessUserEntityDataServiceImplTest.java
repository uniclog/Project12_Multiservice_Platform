package local.uniclog.frame_data_access.telegram.service.impl;

import local.uniclog.frame_data_access.telegram.entity.TelegramMyFitnessUserEntity;
import local.uniclog.frame_data_access.telegram.service.TelegramMyFitnessUserEntityDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TelegramMyFitnessUserEntityDataServiceImplTest {
    @Autowired
    private TelegramMyFitnessUserEntityDataService entityDataService;

    @Test
    void setTelegramTORGUserRepository() {
        assertNotNull(entityDataService);
    }

    @ParameterizedTest
    @CsvSource({"123, Name1, true, 0", "456, Name2, false, 4"})
    void save(ArgumentsAccessor arguments) {
        TelegramMyFitnessUserEntity entity = new TelegramMyFitnessUserEntity();
        entity.setUserTelegramId(arguments.getLong(0));
        entity.setUserName(arguments.getString(1));
        entity.setSubscriber(arguments.getBoolean(2));
        entity.setWaterCount(arguments.getInteger(3));
        entityDataService.save(entity);
        TelegramMyFitnessUserEntity newEntity = entityDataService.findByUserTelegramId(arguments.getLong(0));


        assertAll("User properties",
                () -> assertEquals(entity.getId(), newEntity.getId()),
                () -> assertEquals(entity.getUserName(), newEntity.getUserName()),
                () -> assertEquals(entity.getSubscriber(), newEntity.getSubscriber()),
                () -> assertEquals(entity.getWaterCount(), newEntity.getWaterCount()),
                () -> assertEquals(entity, newEntity)
        );

        newEntity.setSubscriber(!arguments.getBoolean(2));
        newEntity.setWaterCount(arguments.getInteger(3) + 2);
        entityDataService.save(newEntity);
        TelegramMyFitnessUserEntity newEntity2 = entityDataService.findByUserTelegramId(arguments.getLong(0));

        int count = entityDataService.findAll().size();
        assertAll("User properties",
                () -> assertEquals(newEntity.getId(), newEntity2.getId()),
                () -> assertEquals(newEntity.getUserName(), newEntity2.getUserName()),
                () -> assertNotEquals(entity.getSubscriber(), newEntity2.getSubscriber()),
                () -> assertNotEquals(entity.getWaterCount(), newEntity2.getWaterCount()),
                () -> assertNotEquals(entity, newEntity2),
                () -> assertEquals(1, count)
        );
    }

    @ParameterizedTest
    @CsvSource({"123, Name1, true, 0, 11", "456, Name2, false, 4, 11"})
    void update(ArgumentsAccessor arguments) {
        TelegramMyFitnessUserEntity entity = new TelegramMyFitnessUserEntity();
        entity.setUserTelegramId(arguments.getLong(0));
        entity.setUserName(arguments.getString(1));
        entity.setSubscriber(arguments.getBoolean(2));
        entity.setWaterCount(arguments.getInteger(3));
        entityDataService.save(entity);
        TelegramMyFitnessUserEntity newEntity = entityDataService.findByUserTelegramId(arguments.getLong(0));
        newEntity.setWaterCount(arguments.getInteger(4));
        entityDataService.update(newEntity); assertAll("User properties",
                () -> assertEquals(entity.getId(), newEntity.getId()),
                () -> assertEquals(entity.getUserName(), newEntity.getUserName()),
                () -> assertEquals(entity.getSubscriber(), newEntity.getSubscriber()),
                () -> assertNotEquals(entity.getWaterCount(), newEntity.getWaterCount())
        );
    }

    @Test
    void findByUserTelegramId() {
        Long id = 123123L;
        TelegramMyFitnessUserEntity entity = new TelegramMyFitnessUserEntity();
        entity.setUserTelegramId(id);
        entityDataService.save(entity);
        TelegramMyFitnessUserEntity newEntity = entityDataService.findByUserTelegramId(id);
        assertEquals(entity, newEntity);
    }

    @ParameterizedTest
    @CsvSource({
            "123, false, 456, false, 0",
            "123, true, 456, false, 1",
            "123, true, 456, true, 2"})
    void findAllSubscribers(ArgumentsAccessor arguments) {
        TelegramMyFitnessUserEntity entityTestSub1 = new TelegramMyFitnessUserEntity();
        entityTestSub1.setUserTelegramId(arguments.getLong(0));
        entityTestSub1.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entityTestSub1);
        TelegramMyFitnessUserEntity entityTestSub2 = new TelegramMyFitnessUserEntity();
        entityTestSub2.setUserTelegramId(arguments.getLong(2));
        entityTestSub2.setSubscriber(arguments.getBoolean(3));
        entityDataService.save(entityTestSub2);
        int subCount = entityDataService.findAllSubscribers().size();
        assertEquals(arguments.getInteger(4), subCount);
    }

    @Test
    void findAll() {
        TelegramMyFitnessUserEntity entityTestSub1 = new TelegramMyFitnessUserEntity();
        entityTestSub1.setUserTelegramId(123L);
        entityDataService.save(entityTestSub1);
        assertEquals(1, entityDataService.findAll().size());
        TelegramMyFitnessUserEntity entityTestSub2 = new TelegramMyFitnessUserEntity();
        entityTestSub2.setUserTelegramId(456L);
        entityDataService.save(entityTestSub2);
        assertEquals(2, entityDataService.findAll().size());
    }

    @Test
    void deleteAllByUserTelegramId() {
        TelegramMyFitnessUserEntity user = new TelegramMyFitnessUserEntity();
        user.setUserTelegramId(123L);
        entityDataService.save(user);
        TelegramMyFitnessUserEntity delUser = entityDataService.findByUserTelegramId(user.getUserTelegramId());
        List<TelegramMyFitnessUserEntity> deleted = entityDataService.deleteAllByUserTelegramId(delUser.getUserTelegramId());
        assertNotNull(deleted);
    }
}