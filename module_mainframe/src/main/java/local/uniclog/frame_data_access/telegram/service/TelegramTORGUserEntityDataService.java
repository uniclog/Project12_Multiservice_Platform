package local.uniclog.frame_data_access.telegram.service;

import local.uniclog.frame_data_access.telegram.entity.TelegramTORGUserEntity;

import java.util.List;

public interface TelegramTORGUserEntityDataService {
    void save(TelegramTORGUserEntity user);
    void update(TelegramTORGUserEntity user);
    TelegramTORGUserEntity findByUserTelegramId(Long userid);
    List<TelegramTORGUserEntity> findAllSubscribers();
    List<TelegramTORGUserEntity> findAll();
    List<TelegramTORGUserEntity> deleteAllByUserTelegramId(Long id);
}