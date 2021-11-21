package local.uniclog.frame_dataaccess.service;

import local.uniclog.frame_dataaccess.entity.TelegramUnicBotCoreUserEntity;

import java.util.List;

public interface TelegramUnicBotCoreUserService {
    void save(TelegramUnicBotCoreUserEntity user);
    void update(TelegramUnicBotCoreUserEntity user);
    TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramUnicBotCoreUserEntity> findAllSubscribers();
    List<TelegramUnicBotCoreUserEntity> findAll();
}
