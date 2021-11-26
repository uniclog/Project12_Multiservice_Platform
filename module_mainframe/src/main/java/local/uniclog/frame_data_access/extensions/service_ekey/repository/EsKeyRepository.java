package local.uniclog.frame_data_access.extensions.service_ekey.repository;

import local.uniclog.frame_data_access.extensions.service_ekey.entity.EsKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EsKeyRepository extends JpaRepository<EsKeyEntity, Long> {
    EsKeyEntity findByKey(String key);
    List<EsKeyEntity> findByDateAfter(LocalDateTime date);
}