package local.uniclog.frame_dataaccess.service;

import local.uniclog.frame_dataaccess.entity.TeamspeakUserEntity;

import java.util.List;

public interface TeamspeakUserService {
    void save(TeamspeakUserEntity user);
    void update(TeamspeakUserEntity user);
    TeamspeakUserEntity findByTeamspeakToken(String token);
    List<TeamspeakUserEntity> findAllSubscribers();
    List<TeamspeakUserEntity> findAll();
}
