package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDto;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataAccessService;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Реализация сервиса {@link TelegramTORGUserEntityDataAccessService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramTORGUserEntityDataAccessServiceImpl implements TelegramTORGUserEntityDataAccessService {
    public final TelegramTORGUserEntityDataService service;

    @Override
    public <T> TelegramTORGUserEntityDto save(T object) {
        if (object instanceof TelegramTORGUserEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.save(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public <T> TelegramTORGUserEntityDto update(T object) {
        if (object instanceof TelegramTORGUserEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.update(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public TelegramTORGUserEntityDto findByUserTelegramId(Long userTelegramId) {
        var entity = service.findByUserTelegramId(userTelegramId);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public List<TelegramTORGUserEntityDto> findAllSubscribers() {
        var entities = service.findAllSubscribers();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TelegramTORGUserEntityDto> findAll() {
        var entities = service.findAll();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TelegramTORGUserEntityDto> deleteAllByUserTelegramId(Long id) {
        var entities = service.deleteAllByUserTelegramId(id);
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }
}
