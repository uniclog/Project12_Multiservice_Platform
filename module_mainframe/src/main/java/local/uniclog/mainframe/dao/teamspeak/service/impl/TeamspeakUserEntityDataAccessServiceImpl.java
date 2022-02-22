package local.uniclog.mainframe.dao.teamspeak.service.impl;

import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataAccessService;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Реализация сервиса {@link TeamspeakUserEntityDataAccessService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamspeakUserEntityDataAccessServiceImpl implements TeamspeakUserEntityDataAccessService {
    public final TeamspeakUserEntityDataService service;

    @Override
    public TeamspeakUserEntityDataTransferObject save(TeamspeakUserEntityDataTransferObject user) {
        var entity = service.convertFromDataTransferObject(user);
        entity = service.save(entity);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public TeamspeakUserEntityDataTransferObject update(TeamspeakUserEntityDataTransferObject user) {
        var entity = service.convertFromDataTransferObject(user);
        entity = service.update(entity);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public TeamspeakUserEntityDataTransferObject findByTeamspeakToken(String token) {
        var entity = service.findByTeamspeakToken(token);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public List<TeamspeakUserEntityDataTransferObject> findAllSubscribers() {
        var entities = service.findAllSubscribers();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TeamspeakUserEntityDataTransferObject> findAll() {
        var entities = service.findAll();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TeamspeakUserEntityDataTransferObject> deleteByTeamspeakToken(String token) {
        var entities = service.deleteByTeamspeakToken(token);
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }
}
