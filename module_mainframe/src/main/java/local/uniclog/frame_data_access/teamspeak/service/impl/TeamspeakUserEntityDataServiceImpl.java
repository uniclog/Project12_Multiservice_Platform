package local.uniclog.frame_data_access.teamspeak.service.impl;

import local.uniclog.frame_data_access.teamspeak.entity.TeamspeakUserEntity;
import local.uniclog.frame_data_access.teamspeak.repository.TeamspeakUserRepository;
import local.uniclog.frame_data_access.teamspeak.service.TeamspeakUserEntityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamspeakUserEntityDataServiceImpl implements TeamspeakUserEntityDataService {
    private TeamspeakUserRepository teamspeakUserRepository;
    @Autowired
    public void setTeamspeakUserRepository(TeamspeakUserRepository repository) {
        this.teamspeakUserRepository = repository;
    }

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
