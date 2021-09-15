package local.ts3snet.unicbot_ms_spring.core.service.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TeamspeakUserEntity;
import local.ts3snet.unicbot_ms_spring.core.repository.TeamspeakUserRepository;
import local.ts3snet.unicbot_ms_spring.core.service.TeamspeakUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamspeakUserServiceImpl implements TeamspeakUserService {
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
        // business logic
        teamspeakUserRepository.save(userEntity);
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
