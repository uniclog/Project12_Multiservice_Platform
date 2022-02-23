package local.uniclog.mainframe.dao.teamspeak.service.impl;

import local.uniclog.mainframe.dao.DataServiceTestConfiguration;
import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataAccessService;
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
class TeamspeakUserEntityDataAccessServiceImplTest {
    private final String token = "token";
    private final Boolean subscriber = true;
    private final Integer entityId = 1;
    @Autowired
    @Qualifier("beanTeamspeakUserEntityDataAccessServiceTest")
    private TeamspeakUserEntityDataAccessService service;
    private TeamspeakUserEntityDataTransferObject entity;

    @BeforeEach
    void setUp() {
        entity = TeamspeakUserEntityDataTransferObject.builder()
                .teamspeakToken(token)
                .subscriber(subscriber)
                .build();
    }

    @Test
    void setTeamspeakUserEntityDataService() {
        assertNotNull(service);
    }

    @Test
    void save() {
        assertAll("Save tests:",
                () -> assertEquals(service.save(entity).getTeamspeakToken(), entity.getTeamspeakToken()),
                () -> assertNull(service.save("entity")),
                () -> assertNull(service.save(null))
        );
    }

    @Test
    void update() {
        assertAll("Update tests:",
                () -> assertEquals(service.update(entity).getTeamspeakToken(), entity.getTeamspeakToken()),
                () -> {
                    entity.setSubscriber(!subscriber);
                    var result = service.update(entity);
                    assertEquals(result.getTeamspeakToken(), entity.getTeamspeakToken());
                },
                () -> assertNull(service.update(null)),
                () -> assertNull(service.update("entity"))
        );
    }

    @Test
    void findByTeamspeakToken() {
        service.save(entity);
        assertEquals(service.findByTeamspeakToken(token).getTeamspeakToken(), entity.getTeamspeakToken());
    }

    @Test
    void findAllSubscribers() {
        assertTrue(service.findAllSubscribers().isEmpty());

        service.save(entity);
        entity.setId(entityId + 1);
        entity.setTeamspeakToken("otherTokenTrue");
        entity.setSubscriber(false);
        service.save(entity);

        assertEquals(1, service.findAllSubscribers().size());
    }

    @Test
    void findAll() {
        assertTrue(service.findAll().isEmpty());

        service.save(entity);
        entity.setId(entityId + 1);
        entity.setTeamspeakToken("otherToken");
        entity.setSubscriber(false);
        service.save(entity);

        assertEquals(2, service.findAll().size());
    }

    @Test
    void deleteByTeamspeakToken() {
        String tokenForDelete = "otherToken";
        assertTrue(service.deleteByTeamspeakToken(tokenForDelete).isEmpty());

        service.save(entity);
        entity.setId(entityId + 1);
        entity.setTeamspeakToken(tokenForDelete);
        entity.setSubscriber(false);
        service.save(entity);

        assertAll(
                () -> assertEquals(1, service.deleteByTeamspeakToken(tokenForDelete).size()),
                () -> assertNull(service.findByTeamspeakToken(tokenForDelete))
        );
    }
}