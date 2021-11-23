package local.uniclog.frame_data_access.service.impl;

import local.uniclog.frame_data_access.entity.TeamspeakUserEntity;
import local.uniclog.frame_data_access.repository.TeamspeakUserRepository;
import local.uniclog.frame_data_access.service.TeamspeakUserEntityDataService;
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
}
