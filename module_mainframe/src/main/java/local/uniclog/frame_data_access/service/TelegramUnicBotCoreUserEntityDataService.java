package local.uniclog.frame_data_access.service;

import local.uniclog.frame_data_access.entity.TelegramUnicBotCoreUserEntity;

import java.util.List;

public interface TelegramUnicBotCoreUserEntityDataService {
    void save(TelegramUnicBotCoreUserEntity user);
    void update(TelegramUnicBotCoreUserEntity user);
    TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramUnicBotCoreUserEntity> findAllSubscribers();
    List<TelegramUnicBotCoreUserEntity> findAll();
}
