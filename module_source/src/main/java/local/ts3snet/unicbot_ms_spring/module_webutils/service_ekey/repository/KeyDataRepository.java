package local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.repository;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.entity.KeyDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface KeyDataRepository extends JpaRepository<KeyDataEntity, Long> {
    KeyDataEntity findByKey(String key);
    List<KeyDataEntity> findByDateAfter(LocalDateTime date);
}