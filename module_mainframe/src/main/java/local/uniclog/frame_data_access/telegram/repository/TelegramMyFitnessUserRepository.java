package local.uniclog.frame_data_access.telegram.repository;

import local.uniclog.frame_data_access.telegram.entity.TelegramMyFitnessUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramMyFitnessUserRepository extends JpaRepository<TelegramMyFitnessUserEntity, Long> {
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);
    List<TelegramMyFitnessUserEntity> findBySubscriber(boolean subscriber);
    List<TelegramMyFitnessUserEntity> findAllByUserTelegramId(Long userTelegramId);
    void deleteAllByUserTelegramId(Long id);
}
