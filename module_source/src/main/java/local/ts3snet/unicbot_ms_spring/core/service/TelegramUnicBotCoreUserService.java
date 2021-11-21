package local.ts3snet.unicbot_ms_spring.core.service;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramUnicBotCoreUserEntity;

import java.util.List;

public interface TelegramUnicBotCoreUserService {
    void save(TelegramUnicBotCoreUserEntity user);
    void update(TelegramUnicBotCoreUserEntity user);
    TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramUnicBotCoreUserEntity> findAllSubscribers();
    List<TelegramUnicBotCoreUserEntity> findAll();
}
