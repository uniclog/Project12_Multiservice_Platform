package local.uniclog.data_access.teamspeak.service.impl;

import local.uniclog.data_access.DataServiceTestConfiguration;
import local.uniclog.mainframe.dao.teamspeak.entity.TeamspeakUserEntity;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@ContextConfiguration(classes = DataServiceTestConfiguration.class)
class TeamspeakUserEntityDataServiceImplTest {
    @Autowired
    @Qualifier("beanTeamspeakUserEntityDataServiceTest")
    private TeamspeakUserEntityDataService entityDataService;

    private final String token = "token";
    private final Boolean subscriber = true;
    private TeamspeakUserEntity entity;

    @BeforeEach
    void setUp() {
        entity = new TeamspeakUserEntity();
        entity.setTeamspeakToken(token);
        entity.setSubscriber(subscriber);
        entityDataService.save(entity);
    }

    @Test
    void setTeamspeakUserRepository() {
        assertNotNull(entityDataService);
        assertNotNull(entityDataService.findByTeamspeakToken(token));
    }

    @Test
    void save() {
        assertNotNull(entityDataService.findByTeamspeakToken(token));
        assertEquals(entity.getSubscriber(),entityDataService.findByTeamspeakToken(token).getSubscriber());

        TeamspeakUserEntity temp = new TeamspeakUserEntity();
        temp.setTeamspeakToken(token);
        temp.setSubscriber(!subscriber);
        entityDataService.save(temp);

        assertEquals(!subscriber,entityDataService.findByTeamspeakToken(token).getSubscriber());
    }

    @Test
    void update() {
        TeamspeakUserEntity temp = entityDataService.findByTeamspeakToken(token);
        temp.setSubscriber(!subscriber);
        entityDataService.update(temp);
        assertNotEquals(subscriber, entityDataService.findByTeamspeakToken(token).getSubscriber());

        temp = new TeamspeakUserEntity();
        temp.setTeamspeakToken(token + token);
        entityDataService.update(temp);
        assertNotNull(entityDataService.findByTeamspeakToken(token + token));
    }

    @Test
    void findByTeamspeakToken() {
        TeamspeakUserEntity temp = entityDataService.findByTeamspeakToken(token);
        assertEquals(entity, temp);
    }

    @Test
    void findAllSubscribers() {
        assertEquals(1, entityDataService.findAllSubscribers().size());
        TeamspeakUserEntity temp = new TeamspeakUserEntity();
        temp.setTeamspeakToken("otherTokenTrue");
        temp.setSubscriber(true);
        entityDataService.save(temp);
        TeamspeakUserEntity tempFalse = new TeamspeakUserEntity();
        tempFalse.setTeamspeakToken("otherTokenFalse");
        tempFalse.setSubscriber(false);
        entityDataService.save(tempFalse);
        assertEquals(2, entityDataService.findAllSubscribers().size());
    }

    @Test
    void findAll() {
        assertEquals(1, entityDataService.findAll().size());
        TeamspeakUserEntity temp = new TeamspeakUserEntity();
        temp.setTeamspeakToken("temp");
        entityDataService.save(temp);
        assertEquals(2, entityDataService.findAll().size());
    }

    @Test
    void deleteByTeamspeakToken() {
        TeamspeakUserEntity temp = new TeamspeakUserEntity();
        temp.setTeamspeakToken("otherTokenTrue");
        temp.setSubscriber(true);
        entityDataService.save(temp);
        TeamspeakUserEntity delUser = entityDataService.findByTeamspeakToken(token);
        List<TeamspeakUserEntity> deletedList = entityDataService.deleteByTeamspeakToken(delUser.getTeamspeakToken());
        List<TeamspeakUserEntity> notFoundEntity = entityDataService.deleteByTeamspeakToken(delUser.getTeamspeakToken());
        assertAll(
                () -> assertEquals(1, deletedList.size()),
                () -> assertNotNull(deletedList),
                () -> assertNull(notFoundEntity)
        );
    }
}