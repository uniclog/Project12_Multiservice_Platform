package local.ts3snet.unicbotgespring.repository;

import local.ts3snet.unicbotgespring.entity.SetKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetKeyRepository extends JpaRepository<SetKeyEntity, Long> {
    SetKeyEntity findByKey(String key);
}
