package local.ts3snet.unicbot_ms_spring.core.service;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramMyFitnessUserEntity;

import java.util.List;

public interface TelegramMyFitnessUserService {
    void save(TelegramMyFitnessUserEntity user);
    void update(TelegramMyFitnessUserEntity user);
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramMyFitnessUserEntity> findAllSubscribers();
    List<TelegramMyFitnessUserEntity> findAll();
}
