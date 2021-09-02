package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.entity.SetKeyEntity;
import local.ts3snet.unicbotgespring.repository.SetKeyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class DataBaseKeysServiceTest {
    @Autowired
    private SetKeyRepository setKeyRepository;

    @Test
    void contextTest() {
        assertNotNull(setKeyRepository);
    }

    @Test
    void insertKeys() {
        SetKeyEntity key = new SetKeyEntity();
        key.setKey("QWER-ASDF-ASDQ-DSAQ-1234");
        setKeyRepository.save(key);
        SetKeyEntity keyEntity = setKeyRepository.findByKey(key.getKey());
        log.info(keyEntity.toString());
        assertEquals(keyEntity.getKey(), key.getKey());
    }
}