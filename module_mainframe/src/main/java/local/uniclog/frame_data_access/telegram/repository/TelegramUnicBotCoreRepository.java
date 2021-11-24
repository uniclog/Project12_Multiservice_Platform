package local.uniclog.frame_data_access.telegram.repository;

import local.uniclog.frame_data_access.telegram.entity.TelegramUnicBotCoreUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramUnicBotCoreRepository extends JpaRepository<TelegramUnicBotCoreUserEntity, Long> {
    TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramUnicBotCoreUserEntity> findBySubscriber(boolean subscriber);
    List<TelegramUnicBotCoreUserEntity> findAllByUserTelegramId(Long userTelegramId);
    void deleteAllByUserTelegramId(Long id);
}
