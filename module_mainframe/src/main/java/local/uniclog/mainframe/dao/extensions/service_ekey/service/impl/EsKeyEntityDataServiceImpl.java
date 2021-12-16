package local.uniclog.mainframe.dao.extensions.service_ekey.service.impl;

import local.uniclog.mainframe.dao.extensions.service_ekey.entity.EsKeyEntity;
import local.uniclog.mainframe.dao.extensions.service_ekey.repository.EsKeyRepository;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация сервиса {@link EsKeyEntityDataService}
 * @version 0.1
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EsKeyEntityDataServiceImpl implements EsKeyEntityDataService {
    private final EsKeyRepository repository;

    @Override
    public void save(EsKeyEntity key) {
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
        EsKeyEntity entity = repository.findByKeyValue(key);
        if (entity == null) return null;
        this.delete(entity);
        return entity;
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
        return repository.findByKeyValue(key);
    }
}
