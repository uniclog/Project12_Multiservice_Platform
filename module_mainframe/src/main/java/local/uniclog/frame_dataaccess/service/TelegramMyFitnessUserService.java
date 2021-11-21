package local.uniclog.frame_dataaccess.service;

import local.uniclog.frame_dataaccess.entity.TelegramMyFitnessUserEntity;

import java.util.List;

public interface TelegramMyFitnessUserService {
    void save(TelegramMyFitnessUserEntity user);
    void update(TelegramMyFitnessUserEntity user);
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramMyFitnessUserEntity> findAllSubscribers();
    List<TelegramMyFitnessUserEntity> findAll();
}
