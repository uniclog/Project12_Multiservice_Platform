package local.uniclog.frame_data_access.extensions.service_ekey.service.impl;

import local.uniclog.frame_data_access.extensions.service_ekey.entity.EsKeyEntity;
import local.uniclog.frame_data_access.extensions.service_ekey.service.EsKeyEntityDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {
        "spring.jpa.generate-ddl=true",
        "spring.jpa.hibernate.ddl-auto=create"
})
class EsKeyEntityDataServiceImplTest {
    @Autowired
    private EsKeyEntityDataService entityDataService;
    private String key;
    private EsKeyEntity entity;

    @BeforeEach
    void setUp() {
        key = "QWER-ASDF-ASDQ-DSAQ-1234";
        entity = new EsKeyEntity();
        entity.setKey(key);
        entityDataService.save(entity);
    }

    @Test
    void setEsKeyEntityDataService() {
        assertNotNull(entityDataService);
        assertNotNull(entityDataService.findByKey(key));
    }

    @Test
    void save() {
        EsKeyEntity temp = entityDataService.findByKey(key);
        assertEquals(entity, temp);
    }

    @Test
    void delete() {
        entityDataService.delete(entity);
        assertNull(entityDataService.findByKey(key));
    }

    @Test
    void deleteAll() {
        entity = new EsKeyEntity();
        entity.setKey("1234-ASDF-ASDQ-DSAQ-1234");
        entityDataService.save(entity);
        assertEquals(2, entityDataService.findAll().size());
        entityDataService.deleteAll();
        assertEquals(0, entityDataService.findAll().size());
    }

    @Test
    void deleteByKey() {
        entityDataService.deleteByKey(key);
        assertNull(entityDataService.findByKey(key));
    }

    @Test
    void update() {
        String newKey = "1234-1234-1234-1234-1234";
        entity = entityDataService.findByKey(key);
        entity.setKey(newKey);
        entityDataService.update(entity);
        assertEquals(newKey, entityDataService.findByKey(newKey).getKey());
    }

    @Test
    void findByDateAfter() {
        entity = new EsKeyEntity();
        entity.setKey("1234-1234-1234-1234-1234");
        entity.setDate(LocalDateTime.now().plusDays(10)); // Now date + 10 days
        entityDataService.save(entity);
        // Entity count check in 5 days
        assertEquals(1, entityDataService.findByDateAfter(LocalDateTime.now().plusDays(5)).size());

        entity = new EsKeyEntity();
        entity.setKey("9999-9999-9999-9999-9999");
        entity.setDate(LocalDateTime.now().plusDays(15)); // Now date + 15 days
        entityDataService.save(entity);
        // Entity count check in 5 days
        assertEquals(2, entityDataService.findByDateAfter(LocalDateTime.now().plusDays(5)).size());
    }

    @Test
    void findAll() {
        entity = new EsKeyEntity();
        entity.setKey("1234-1234-1234-1234-1234");
        entityDataService.save(entity);
        // Entity count check
        assertEquals(2, entityDataService.findAll().size());
    }

    @Test
    void findByKey() {
        assertEquals(key, entityDataService.findByKey(key).getKey());
    }
}