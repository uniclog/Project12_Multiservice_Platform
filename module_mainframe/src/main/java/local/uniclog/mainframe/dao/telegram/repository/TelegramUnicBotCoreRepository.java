package local.uniclog.mainframe.dao.telegram.repository;

import local.uniclog.mainframe.dao.telegram.entity.TelegramUnicBotCoreUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramUnicBotCoreRepository extends JpaRepository<TelegramUnicBotCoreUserEntity, Long> {
    TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramUnicBotCoreUserEntity> findBySubscriber(boolean subscriber);
    List<TelegramUnicBotCoreUserEntity> findAllByUserTelegramId(Long userTelegramId);
    void deleteAllByUserTelegramId(Long id);
}
