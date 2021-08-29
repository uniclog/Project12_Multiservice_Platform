package local.ts3snet.unicbotgespring.repository;

import local.ts3snet.unicbotgespring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserTelegramId(Long userTelegramId);
    List<UserEntity> findAllBySubscriber(boolean subscriber);
}
