package local.uniclog.mainframe.dao.teamspeak.service.impl;

import local.uniclog.mainframe.dao.teamspeak.entity.TeamspeakUserEntity;
import local.uniclog.mainframe.dao.teamspeak.repository.TeamspeakUserRepository;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса {@link TeamspeakUserEntityDataService}
 * @version 0.1
 */
@Service
@RequiredArgsConstructor
public class TeamspeakUserEntityDataServiceImpl implements TeamspeakUserEntityDataService {
    private final TeamspeakUserRepository teamspeakUserRepository;

    @Override
    public void save(TeamspeakUserEntity user) {
        TeamspeakUserEntity userEntity = teamspeakUserRepository.findByTeamspeakToken(user.getTeamspeakToken());
        if (userEntity != null) {
            update(user);
            return;
        }
        teamspeakUserRepository.save(user);
    }

    @Override
    public void update(TeamspeakUserEntity user) {
        TeamspeakUserEntity userEntity = teamspeakUserRepository.findByTeamspeakToken(user.getTeamspeakToken());
        if (userEntity == null) {
            save(user);
            return;
        }
        user.setId(userEntity.getId());
        teamspeakUserRepository.save(user);
    }

    @Override
    public TeamspeakUserEntity findByTeamspeakToken(String token) {
        return teamspeakUserRepository.findByTeamspeakToken(token);
    }

    @Override
    public List<TeamspeakUserEntity> findAllSubscribers() {
        return teamspeakUserRepository.findAllBySubscriber(true);
    }

    @Override
    public List<TeamspeakUserEntity> findAll() {
        return teamspeakUserRepository.findAll();
    }

    @Override
    public List<TeamspeakUserEntity> deleteByTeamspeakToken(String token) {
        List<TeamspeakUserEntity> users = teamspeakUserRepository.findAllByTeamspeakToken(token);
        if (users.isEmpty()) return null;
        teamspeakUserRepository.deleteAllByTeamspeakToken(token);
        return users;
    }
}