package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.service.TelegramMyFitnessUserEntityDataAccessService;
import local.uniclog.mainframe.dao.telegram.service.TelegramMyFitnessUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Реализация сервиса {@link TelegramMyFitnessUserEntityDataAccessService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramMyFitnessUserEntityDataAccessServiceImpl implements TelegramMyFitnessUserEntityDataAccessService {
    public final TelegramMyFitnessUserEntityDataService service;

    @Override
    public <T> TelegramMyFitnessUserEntityDataTransferObject save(T object) {
        if (object instanceof TelegramMyFitnessUserEntityDataTransferObject entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.save(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public <T> TelegramMyFitnessUserEntityDataTransferObject update(T object) {
        if (object instanceof TelegramMyFitnessUserEntityDataTransferObject entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.update(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public TelegramMyFitnessUserEntityDataTransferObject findByUserTelegramId(Long userTelegramId) {
        var entity = service.findByUserTelegramId(userTelegramId);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public List<TelegramMyFitnessUserEntityDataTransferObject> findAllSubscribers() {
        var entities = service.findAllSubscribers();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TelegramMyFitnessUserEntityDataTransferObject> findAll() {
        var entities = service.findAll();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TelegramMyFitnessUserEntityDataTransferObject> deleteAllByUserTelegramId(Long id) {
        var entities = service.deleteAllByUserTelegramId(id);
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }
}
