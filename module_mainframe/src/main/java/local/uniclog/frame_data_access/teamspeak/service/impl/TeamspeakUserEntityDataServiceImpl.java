package local.uniclog.frame_data_access.teamspeak.service.impl;

import local.uniclog.frame_data_access.teamspeak.entity.TeamspeakUserEntity;
import local.uniclog.frame_data_access.teamspeak.repository.TeamspeakUserRepository;
import local.uniclog.frame_data_access.teamspeak.service.TeamspeakUserEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
