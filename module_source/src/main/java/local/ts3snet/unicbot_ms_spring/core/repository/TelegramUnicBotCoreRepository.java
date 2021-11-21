package local.ts3snet.unicbot_ms_spring.core.repository;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramUnicBotCoreUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramUnicBotCoreRepository extends JpaRepository<TelegramUnicBotCoreUserEntity, Long> {
    TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramUnicBotCoreUserEntity> findBySubscriber(boolean subscriber);
}
