package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.service;

import local.ts3snet.unicbot_ms_spring.core.entity.CoreUserEntity;
import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.entity.KeyDataEntity;

import java.util.List;

public interface KeyDataService {
    void save(KeyDataEntity key);
    void delete(KeyDataEntity key);
    void update(KeyDataEntity key);
    List<KeyDataEntity> findByDate();
    List<KeyDataEntity> findAll();
}
