package local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.service;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.entity.KeyDataEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface KeyDataService {
    void save(KeyDataEntity key);
    void delete(KeyDataEntity key);
    void deleteAll();
    void update(KeyDataEntity key);
    List<KeyDataEntity> findByDateAfter(LocalDateTime date);
    List<KeyDataEntity> findAll();
}
