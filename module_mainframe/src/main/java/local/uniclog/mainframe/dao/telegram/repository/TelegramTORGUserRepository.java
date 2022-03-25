package local.uniclog.mainframe.dao.telegram.repository;

import local.uniclog.mainframe.dao.telegram.entity.TelegramTORGUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for TelegramTORGUser
 *
 * @author uniclog
 * @version 0.1
 */
public interface TelegramTORGUserRepository extends JpaRepository<TelegramTORGUserEntity, Long> {
    TelegramTORGUserEntity findByUserTelegramId(Long userTelegramId);

    List<TelegramTORGUserEntity> findAllBySubscriber(boolean subscriber);

    List<TelegramTORGUserEntity> findAllByUserTelegramId(Long userTelegramId);

    void deleteAllByUserTelegramId(Long id);
}
