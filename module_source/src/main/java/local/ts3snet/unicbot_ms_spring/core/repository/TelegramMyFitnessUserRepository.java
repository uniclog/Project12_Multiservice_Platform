package local.ts3snet.unicbot_ms_spring.core.repository;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramMyFitnessUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramMyFitnessUserRepository extends JpaRepository<TelegramMyFitnessUserEntity, Long> {
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramMyFitnessUserEntity> findBySubscriber(boolean subscriber);
}
