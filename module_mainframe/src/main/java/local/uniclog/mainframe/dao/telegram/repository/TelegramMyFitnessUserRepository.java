package local.uniclog.mainframe.dao.telegram.repository;

import local.uniclog.mainframe.dao.telegram.entity.TelegramMyFitnessUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for TelegramMyFitnessUser
 *
 * @author uniclog
 * @version 0.1
 */
public interface TelegramMyFitnessUserRepository extends JpaRepository<TelegramMyFitnessUserEntity, Long> {
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);

    List<TelegramMyFitnessUserEntity> findBySubscriber(boolean subscriber);

    List<TelegramMyFitnessUserEntity> findAllByUserTelegramId(Long userTelegramId);

    void deleteAllByUserTelegramId(Long id);
}
