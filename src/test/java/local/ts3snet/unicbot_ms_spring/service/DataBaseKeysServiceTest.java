package local.ts3snet.unicbot_ms_spring.service;

import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.entity.KeyDataEntity;
import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.repository.KeyDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class DataBaseKeysServiceTest {
    @Autowired
    private KeyDataRepository keyDataRepository;

    @Test
    void contextTest() {
        assertNotNull(keyDataRepository);
    }

    @Test
    void insertKeys() {
        KeyDataEntity key = new KeyDataEntity();
        key.setKey("QWER-ASDF-ASDQ-DSAQ-1234");
        keyDataRepository.save(key);
        KeyDataEntity keyEntity = keyDataRepository.findByKey(key.getKey());
        log.info(keyEntity.toString());
        assertEquals(keyEntity.getKey(), key.getKey());
    }
}