package local.uniclog.frame_data_access.telegram.service;

import local.uniclog.frame_data_access.telegram.entity.TelegramMyFitnessUserEntity;

import java.util.List;

public interface TelegramMyFitnessUserEntityDataService {
    void save(TelegramMyFitnessUserEntity user);
    void update(TelegramMyFitnessUserEntity user);
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramMyFitnessUserEntity> findAllSubscribers();
    List<TelegramMyFitnessUserEntity> findAll();
    List<TelegramMyFitnessUserEntity> deleteAllByUserTelegramId(Long id);
}
