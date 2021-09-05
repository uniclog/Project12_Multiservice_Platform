package local.ts3snet.unicbot_ms_spring.core.service;

import local.ts3snet.unicbot_ms_spring.core.entity.CoreUserEntity;

import java.util.List;

public interface CoreUserService {
    void save(CoreUserEntity user);
    void update(CoreUserEntity user);
    CoreUserEntity findByUserTelegramId(Long userid);
    List<CoreUserEntity> findAllSubscribers();
    List<CoreUserEntity> findAll();
}