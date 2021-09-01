package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.entity.SetKeyEntity;

import java.util.List;

public interface SetKeyService {
    void save(SetKeyEntity key);
    void delete(SetKeyEntity key);
    void update(SetKeyEntity key);
    List<SetKeyEntity> findByDate();
}
