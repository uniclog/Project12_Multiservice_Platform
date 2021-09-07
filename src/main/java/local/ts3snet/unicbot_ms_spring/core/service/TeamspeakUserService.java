package local.ts3snet.unicbot_ms_spring.core.service;

import local.ts3snet.unicbot_ms_spring.core.entity.TeamspeakUserEntity;

import java.util.List;

public interface TeamspeakUserService {
    void save(TeamspeakUserEntity user);
    void update(TeamspeakUserEntity user);
    TeamspeakUserEntity findByTeamspeakToken(String token);
    List<TeamspeakUserEntity> findAll();
}
