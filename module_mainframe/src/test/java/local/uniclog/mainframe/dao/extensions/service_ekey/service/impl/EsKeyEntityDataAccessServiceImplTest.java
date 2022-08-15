package local.uniclog.mainframe.dao.extensions.service_ekey.service.impl;

import local.uniclog.mainframe.dao.DataServiceTestConfiguration;
import local.uniclog.mainframe.dao.extensions.service_ekey.dto.EsKeyEntityDto;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataAccessService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@ContextConfiguration(classes = DataServiceTestConfiguration.class)
class EsKeyEntityDataAccessServiceImplTest {
    private static final String key = "QWER-ASDF-ASDQ-DSAQ-1234";
    @Autowired
    @Qualifier("beanEsKeyEntityDataAccessServiceTest")
    private EsKeyEntityDataAccessService service;
    private EsKeyEntityDto entity;

    @BeforeEach
    void setUp() {
        entity = EsKeyEntityDto.builder()
                .keyValue(key)
                .date(LocalDateTime.now())
                .build();
    }

    @Test
    void setEsKeyEntityDataAccessService() {
        assertNotNull(service);
    }

    @Test
    void save() {
        assertAll("Save tests:",
                () -> assertEquals(service.save(entity).getKeyValue(), entity.getKeyValue()),
                () -> assertNull(service.save("entity")),
                () -> assertNull(service.save(null))
        );
    }

    @Test
    void delete() {
        service.save(entity);

        assertAll(
                () -> assertTrue(service.delete(entity)),
                () -> assertFalse(service.delete(null))
        );
    }

    @Test
    void deleteAll() {
        assertTrue(service.findAll().isEmpty());

        service.save(entity);
        entity = EsKeyEntityDto.builder()
                .keyValue("TEST-TEST-TEST-TEST-TEST")
                .date(LocalDateTime.now())
                .build();
        service.save(entity);

        assertEquals(2, service.findAll().size());
        service.deleteAll();
        assertTrue(service.findAll().isEmpty());
    }

    @Test
    void deleteByKey() {
        String keyForDelete = "TEST-TEST-TEST-TEST-TEST";
        assertTrue(Objects.isNull(service.deleteByKey(keyForDelete)));

        service.save(entity);
        entity = EsKeyEntityDto.builder()
                .keyValue(keyForDelete)
                .date(LocalDateTime.now())
                .build();
        service.save(entity);

        assertAll(
                () -> assertFalse(Objects.isNull(service.deleteByKey(keyForDelete))),
                () -> {
                    assert service != null;
                    assertNull(service.findByKey(keyForDelete));
                }
        );
    }

    @Test
    void findByDateAfter() {
        service.save(entity);
        entity = EsKeyEntityDto.builder()
                .keyValue("TEST-TEST-TEST-TEST-TEST")
                .date(LocalDateTime.now().plusDays(2))
                .build();
        service.save(entity);

        assertEquals(1, service.findByDateAfter(LocalDateTime.now().plusDays(1)).size());
        assertTrue(service.findByDateAfter(LocalDateTime.now().plusDays(3)).isEmpty());
    }

    @Test
    void findAll() {
        assertTrue(service.findAll().isEmpty());

        service.save(entity);
        entity = EsKeyEntityDto.builder()
                .keyValue("TEST-TEST-TEST-TEST-TEST")
                .date(LocalDateTime.now())
                .build();
        service.save(entity);

        assertEquals(2, service.findAll().size());
    }

    @Test
    void findByKey() {
        service.save(entity);
        assertNotNull(service.findByKey(entity.getKeyValue()));
    }
}