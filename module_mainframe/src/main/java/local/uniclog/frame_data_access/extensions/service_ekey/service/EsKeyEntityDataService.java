package local.uniclog.frame_data_access.extensions.service_ekey.service;

import local.uniclog.frame_data_access.extensions.service_ekey.entity.EsKeyEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EsKeyEntityDataService {
    void save(EsKeyEntity key);
    void delete(EsKeyEntity key);
    void deleteAll();
    void deleteByKey(String key);
    void update(EsKeyEntity key);
    List<EsKeyEntity> findByDateAfter(LocalDateTime date);
    List<EsKeyEntity> findAll();
    EsKeyEntity findByKey(String key);
}
