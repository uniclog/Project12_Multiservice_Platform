package local.uniclog.frame_data_access.service;

import local.uniclog.frame_data_access.entity.TeamspeakUserEntity;

import java.util.List;

public interface TeamspeakUserEntityDataService {
    void save(TeamspeakUserEntity user);
    void update(TeamspeakUserEntity user);
    TeamspeakUserEntity findByTeamspeakToken(String token);
    List<TeamspeakUserEntity> findAllSubscribers();
    List<TeamspeakUserEntity> findAll();
}
