package local.uniclog.mainframe.dao.teamspeak.service.impl;

import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDto;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataAccessService;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Реализация сервиса {@link TeamspeakUserEntityDataAccessService}
 *
 * @version 0.2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamspeakUserEntityDataAccessServiceImpl implements TeamspeakUserEntityDataAccessService {
    public final TeamspeakUserEntityDataService service;

    @Override
    public <T> TeamspeakUserEntityDto save(T object) {
        if (object instanceof TeamspeakUserEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.save(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public <T> TeamspeakUserEntityDto update(T object) {
        if (object instanceof TeamspeakUserEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.update(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public TeamspeakUserEntityDto findByTeamspeakToken(String token) {
        var entity = service.findByTeamspeakToken(token);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public List<TeamspeakUserEntityDto> findAllSubscribers() {
        var entities = service.findAllSubscribers();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .toList();
    }

    @Override
    public List<TeamspeakUserEntityDto> findAll() {
        var entities = service.findAll();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .toList();
    }

    @Override
    public List<TeamspeakUserEntityDto> deleteByTeamspeakToken(String token) {
        var entities = service.deleteByTeamspeakToken(token);
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .toList();
    }
}
