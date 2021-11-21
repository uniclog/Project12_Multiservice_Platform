package local.uniclog.frame_dataaccess.repository;

import local.uniclog.frame_dataaccess.entity.TeamspeakUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamspeakUserRepository extends JpaRepository<TeamspeakUserEntity, Long> {
    TeamspeakUserEntity findByTeamspeakToken(String teamspeakToken);
    List<TeamspeakUserEntity> findAllBySubscriber(boolean subscriber);
}
