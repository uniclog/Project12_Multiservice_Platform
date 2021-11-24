package local.uniclog.temp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
class DataBaseKeysServiceTest {
    //todo как перенесу сущность

    /*@Autowired
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
        Assertions.assertEquals(keyEntity.getKey(), key.getKey());
    }*/
}