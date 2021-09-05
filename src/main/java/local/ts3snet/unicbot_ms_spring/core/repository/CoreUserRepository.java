package local.ts3snet.unicbot_ms_spring.core.repository;

import local.ts3snet.unicbot_ms_spring.core.entity.CoreUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoreUserRepository extends JpaRepository<CoreUserEntity, Long> {
    CoreUserEntity findByUserTelegramId(Long userTelegramId);
    List<CoreUserEntity> findAllBySubscriber(boolean subscriber);
}
