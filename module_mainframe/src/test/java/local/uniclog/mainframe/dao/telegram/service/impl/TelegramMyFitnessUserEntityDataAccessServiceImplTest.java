package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.DataServiceTestConfiguration;
import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.service.TelegramMyFitnessUserEntityDataAccessService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@ContextConfiguration(classes = DataServiceTestConfiguration.class)
class TelegramMyFitnessUserEntityDataAccessServiceImplTest {
    private final Long userTelegramId = 123L;
    private final String userName = "userName";
    private final Boolean subscriber = true;
    private final Integer waterCount = 0;
    @Autowired
    @Qualifier("beanTelegramMyFitnessUserEntityDataAccessServiceTest")
    private TelegramMyFitnessUserEntityDataAccessService service;
    private TelegramMyFitnessUserEntityDataTransferObject entity;


    @BeforeEach
    void setUp() {
        entity = TelegramMyFitnessUserEntityDataTransferObject.builder()
                .userTelegramId(userTelegramId)
                .userName(userName)
                .waterCount(waterCount)
                .subscriber(subscriber)
                .build();
    }

    @Test
    void teamspeakUserEntityDataAccessService() {
        assertNotNull(service);
    }

    @Test
    void save() {
        assertAll("Save tests:",
                () -> assertEquals(service.save(entity).getUserTelegramId(), userTelegramId),
                () -> assertNull(service.save("entity")),
                () -> assertNull(service.save(null))
        );
    }

    @Test
    void update() {
        assertAll("Update tests:",
                () -> assertEquals(service.update(entity).getUserTelegramId(), userTelegramId),
                () -> {
                    entity.setSubscriber(!subscriber);
                    var result = service.update(entity);
                    assertEquals(result.getUserTelegramId(), userTelegramId);
                },
                () -> assertNull(service.update(null)),
                () -> assertNull(service.update("entity"))
        );
    }

    @Test
    void findByUserTelegramId() {
        service.save(entity);
        assertEquals(service.findByUserTelegramId(userTelegramId).getUserTelegramId(), userTelegramId);
    }

    @Test
    void findAllSubscribers() {
        assertTrue(service.findAllSubscribers().isEmpty());

        entity.setId(service.save(entity).getId() + 1);
        entity.setUserTelegramId(456L);
        entity.setSubscriber(false);
        service.save(entity);

        assertEquals(1, service.findAllSubscribers().size());
    }

    @Test
    void findAll() {
        assertTrue(service.findAll().isEmpty());

        entity.setId(service.save(entity).getId() + 1);
        entity.setUserTelegramId(456L);
        entity.setSubscriber(false);
        service.save(entity);

        assertEquals(2, service.findAll().size());
    }

    @Test
    void deleteAllByUserTelegramId() {
        Long idForDelete = 321L;
        assertTrue(service.deleteAllByUserTelegramId(idForDelete).isEmpty());

        entity.setId(service.save(entity).getId() + 1);
        entity.setUserTelegramId(idForDelete);
        entity.setSubscriber(false);
        service.save(entity);

        assertAll(
                () -> assertEquals(1, service.deleteAllByUserTelegramId(idForDelete).size()),
                () -> assertNull(service.findByUserTelegramId(idForDelete))
        );
    }
}