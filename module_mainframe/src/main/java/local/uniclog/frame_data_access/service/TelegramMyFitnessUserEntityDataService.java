package local.uniclog.frame_data_access.service;

import local.uniclog.frame_data_access.entity.TelegramMyFitnessUserEntity;

import java.util.List;

public interface TelegramMyFitnessUserEntityDataService {
    void save(TelegramMyFitnessUserEntity user);
    void update(TelegramMyFitnessUserEntity user);
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramMyFitnessUserEntity> findAllSubscribers();
    List<TelegramMyFitnessUserEntity> findAll();
}
