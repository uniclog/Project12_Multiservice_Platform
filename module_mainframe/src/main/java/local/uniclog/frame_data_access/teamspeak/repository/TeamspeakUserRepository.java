package local.uniclog.frame_data_access.teamspeak.repository;

import local.uniclog.frame_data_access.teamspeak.entity.TeamspeakUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamspeakUserRepository extends JpaRepository<TeamspeakUserEntity, Long> {
    TeamspeakUserEntity findByTeamspeakToken(String teamspeakToken);
    List<TeamspeakUserEntity> findAllBySubscriber(boolean subscriber);
    List<TeamspeakUserEntity> findAllByTeamspeakToken(String token);
    void deleteAllByTeamspeakToken(String token);
}
