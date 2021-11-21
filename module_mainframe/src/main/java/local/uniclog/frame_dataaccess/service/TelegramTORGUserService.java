package local.uniclog.frame_dataaccess.service;

import local.uniclog.frame_dataaccess.entity.TelegramTORGUserEntity;

import java.util.List;

public interface TelegramTORGUserService {
    void save(TelegramTORGUserEntity user);
    void update(TelegramTORGUserEntity user);
    TelegramTORGUserEntity findByUserTelegramId(Long userid);
    List<TelegramTORGUserEntity> findAllSubscribers();
    List<TelegramTORGUserEntity> findAll();
}