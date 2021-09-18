package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service;

import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.entity.KeyDataEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface KeyDataService {
    void save(KeyDataEntity key);
    void delete(KeyDataEntity key);
    void update(KeyDataEntity key);
    List<KeyDataEntity> findByDateAfter(LocalDateTime date);
    List<KeyDataEntity> findAll();
}
