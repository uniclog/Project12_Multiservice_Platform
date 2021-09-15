package local.ts3snet.unicbot_ms_spring.core.service;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramTORGUserEntity;

import java.util.List;

public interface TelegramTORGUserService {
    void save(TelegramTORGUserEntity user);
    void update(TelegramTORGUserEntity user);
    TelegramTORGUserEntity findByUserTelegramId(Long userid);
    List<TelegramTORGUserEntity> findAllSubscribers();
    List<TelegramTORGUserEntity> findAll();
}