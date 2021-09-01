package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.entity.SetKeyEntity;
import local.ts3snet.unicbotgespring.entity.UserEntity;
import local.ts3snet.unicbotgespring.repository.SetKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetKeyServiceImpl implements SetKeyService {
    private final SetKeyRepository repository;

    public SetKeyServiceImpl(SetKeyRepository repository) {
        this.repository = repository;
    }


    @Override
    public void save(SetKeyEntity key) {
        SetKeyEntity keyEntity = repository.findByKey(key.getKey());
        if (keyEntity != null) {
            update(key);
            return;
        }
        repository.save(key);
    }

    @Override
    public void delete(SetKeyEntity key) {
        repository.delete(key);
    }

    @Override
    public void update(SetKeyEntity key) {
        repository.save(key);
    }

    @Override
    public List<SetKeyEntity> findByDate() {
        return null;
    }
}
