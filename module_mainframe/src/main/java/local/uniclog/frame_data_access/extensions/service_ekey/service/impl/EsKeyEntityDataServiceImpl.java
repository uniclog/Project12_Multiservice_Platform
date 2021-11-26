package local.uniclog.frame_data_access.extensions.service_ekey.service.impl;

import local.uniclog.frame_data_access.extensions.service_ekey.entity.EsKeyEntity;
import local.uniclog.frame_data_access.extensions.service_ekey.repository.EsKeyRepository;
import local.uniclog.frame_data_access.extensions.service_ekey.service.EsKeyEntityDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация интерфейса сервиса {@link EsKeyEntityDataService}
 * @version 0.1
 */
@Service
@Transactional
public class EsKeyEntityDataServiceImpl implements EsKeyEntityDataService {
    private final EsKeyRepository repository;

    public EsKeyEntityDataServiceImpl(EsKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(EsKeyEntity key) {
        EsKeyEntity keyEntity = this.findByKey(key.getKey());
        if (keyEntity != null) {
            this.update(key);
            return;
        }
        repository.save(key);
    }

    @Override
    public void delete(EsKeyEntity key) {
        repository.delete(key);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public EsKeyEntity deleteByKey(String key) {
        EsKeyEntity entity = repository.findByKey(key);
        if (entity == null) return null;
        this.delete(entity);
        return entity;
    }

    @Override
    public void update(EsKeyEntity key) {
        repository.save(key);
    }

    @Override
    public List<EsKeyEntity> findByDateAfter(LocalDateTime date) {
        return repository.findByDateAfter(date);
    }

    @Override
    public List<EsKeyEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public EsKeyEntity findByKey(String key) {
        return repository.findByKey(key);
    }
}
