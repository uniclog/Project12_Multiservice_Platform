package local.uniclog.mainframe.dao.extensions.service_ekey.repository;

import local.uniclog.mainframe.dao.extensions.service_ekey.entity.EsKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EsKeyRepository extends JpaRepository<EsKeyEntity, Long> {
    EsKeyEntity findByKeyValue(String key);
    List<EsKeyEntity> findByDateAfter(LocalDateTime date);
}