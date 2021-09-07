package local.ts3snet.unicbot_ms_spring.core.repository;

import local.ts3snet.unicbot_ms_spring.core.entity.TeamspeakUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamspeakUserRepository extends JpaRepository<TeamspeakUserEntity, Long> {
    TeamspeakUserEntity findByTeamspeakToken(String teamspeakToken);
}
