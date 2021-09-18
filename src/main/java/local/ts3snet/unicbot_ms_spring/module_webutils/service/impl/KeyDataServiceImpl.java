package local.ts3snet.unicbot_ms_spring.module_webutils.service.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.entity.KeyDataEntity;
import local.ts3snet.unicbot_ms_spring.module_webutils.repository.KeyDataRepository;
import local.ts3snet.unicbot_ms_spring.module_webutils.service.KeyDataService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KeyDataServiceImpl implements KeyDataService {
    private final KeyDataRepository repository;

    public KeyDataServiceImpl(KeyDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(KeyDataEntity key) {
        KeyDataEntity keyEntity = repository.findByKey(key.getKey());
        if (keyEntity != null) {
            update(key);
            return;
        }
        repository.save(key);
    }

    @Override
    public void delete(KeyDataEntity key) {
        repository.delete(key);
    }

    @Override
    public void update(KeyDataEntity key) {
        repository.save(key);
    }

    @Override
    public List<KeyDataEntity> findByDateAfter(LocalDateTime date) {
        return repository.findByDateAfter(date);
    }

    @Override
    public List<KeyDataEntity> findAll() {
        return repository.findAll();
    }
}
