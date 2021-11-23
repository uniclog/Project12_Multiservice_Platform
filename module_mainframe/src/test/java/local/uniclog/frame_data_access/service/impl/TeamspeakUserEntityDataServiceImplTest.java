package local.uniclog.frame_data_access.service.impl;

import local.uniclog.frame_data_access.entity.TeamspeakUserEntity;
import local.uniclog.frame_data_access.service.TeamspeakUserEntityDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TeamspeakUserEntityDataServiceImplTest {
    @Autowired
    private TeamspeakUserEntityDataService entityDataService;

    @Test
    void setTeamspeakUserRepository() {
        assertNotNull(entityDataService);
    }

    @ParameterizedTest
    @CsvSource({"token1, true", "token2, false"})
    void save(ArgumentsAccessor arguments) {
        TeamspeakUserEntity entity = new TeamspeakUserEntity();
        entity.setTeamspeakToken(arguments.getString(0));
        entity.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entity);
        TeamspeakUserEntity newEntity = entityDataService.findByTeamspeakToken(arguments.getString(0));
        assertEquals(entity.getSubscriber(), newEntity.getSubscriber());
    }

    @ParameterizedTest
    @CsvSource({"token1, true, newToken", "token2, false,"})
    void update(ArgumentsAccessor arguments) {
        TeamspeakUserEntity entity = new TeamspeakUserEntity();
        entity.setTeamspeakToken(arguments.getString(0));
        entity.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entity);
        TeamspeakUserEntity newEntity = entityDataService.findByTeamspeakToken(arguments.getString(0));
        newEntity.setTeamspeakToken(arguments.getString(2));
        entityDataService.update(newEntity);
        assertEquals(entity.getSubscriber(), newEntity.getSubscriber());
        assertNotEquals(entity.getTeamspeakToken(), newEntity.getTeamspeakToken());
    }

    @ParameterizedTest
    @CsvSource({"token1, true", "token2, false"})
    void findByTeamspeakToken(ArgumentsAccessor arguments) {
        TeamspeakUserEntity entity = new TeamspeakUserEntity();
        entity.setTeamspeakToken(arguments.getString(0));
        entity.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entity);
        TeamspeakUserEntity newEntity = entityDataService.findByTeamspeakToken(arguments.getString(0));
        assertEquals(entity.getSubscriber(), newEntity.getSubscriber());
    }

    @ParameterizedTest
    @CsvSource({
            "token1, false, token2, false, 0",
            "token1, true, token2, false, 1",
            "token1, true, token2, true, 2"})
    void findAllSubscribers(ArgumentsAccessor arguments) {
        TeamspeakUserEntity entityTestSub1 = new TeamspeakUserEntity();
        entityTestSub1.setTeamspeakToken(arguments.getString(0));
        entityTestSub1.setSubscriber(arguments.getBoolean(1));
        entityDataService.save(entityTestSub1);
        TeamspeakUserEntity entityTestSub2 = new TeamspeakUserEntity();
        entityTestSub2.setTeamspeakToken(arguments.getString(2));
        entityTestSub2.setSubscriber(arguments.getBoolean(3));
        entityDataService.save(entityTestSub2);
        int subCount = entityDataService.findAllSubscribers().size();
        assertEquals(subCount, arguments.getInteger(4));
    }

    @Test
    void findAll() {
        TeamspeakUserEntity entityTestSub1 = new TeamspeakUserEntity();
        entityTestSub1.setTeamspeakToken("entityTest1");
        entityDataService.save(entityTestSub1);
        assertEquals(1, entityDataService.findAllSubscribers().size());
        TeamspeakUserEntity entityTestSub2 = new TeamspeakUserEntity();
        entityTestSub2.setTeamspeakToken("entityTest2");
        entityDataService.save(entityTestSub2);
        assertEquals(2, entityDataService.findAllSubscribers().size());
    }
}