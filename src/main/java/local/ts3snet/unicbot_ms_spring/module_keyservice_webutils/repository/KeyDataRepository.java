package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.repository;

import local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.entity.KeyDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface KeyDataRepository extends JpaRepository<KeyDataEntity, Long> {
    KeyDataEntity findByKey(String key);
    List<KeyDataEntity> findByDateAfter(LocalDateTime date);
}