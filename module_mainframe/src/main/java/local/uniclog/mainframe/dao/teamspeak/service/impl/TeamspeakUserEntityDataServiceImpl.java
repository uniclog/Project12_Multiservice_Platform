package local.uniclog.mainframe.dao.teamspeak.service.impl;

import local.uniclog.mainframe.dao.common.DataUtilsService;
import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.teamspeak.entity.TeamspeakUserEntity;
import local.uniclog.mainframe.dao.teamspeak.repository.TeamspeakUserRepository;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Реализация сервиса {@link TeamspeakUserEntityDataService}
 *
 * @version 0.2
 */
@Service
@RequiredArgsConstructor
public class TeamspeakUserEntityDataServiceImpl implements TeamspeakUserEntityDataService {
    private final TeamspeakUserRepository teamspeakUserRepository;
    private final DataUtilsService dataUtilsService;

    @Override
    public <T> TeamspeakUserEntity save(T object) {
        if (object instanceof TeamspeakUserEntity entity) {
            var entityByToken = this.findByTeamspeakToken(entity.getTeamspeakToken());
            if (entityByToken != null) {
                entity.setId(entityByToken.getId());
            }
            return teamspeakUserRepository.save(entity);
        }
        return null;
    }

    @Override
    public <T> TeamspeakUserEntity update(T object) {
        return this.save(object);
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
        if (users.isEmpty()) return emptyList();
        teamspeakUserRepository.deleteAllByTeamspeakToken(token);
        return users;
    }

    @Override
    public TeamspeakUserEntityDataTransferObject convertToDataTransferObject(TeamspeakUserEntity entity) {
        return dataUtilsService.convertToDataTransferObject(entity, TeamspeakUserEntityDataTransferObject.class);
    }

    @Override
    public TeamspeakUserEntity convertFromDataTransferObject(TeamspeakUserEntityDataTransferObject dto) {
        return dataUtilsService.convertFromDataTransferObject(dto, TeamspeakUserEntity.class);
    }
}
