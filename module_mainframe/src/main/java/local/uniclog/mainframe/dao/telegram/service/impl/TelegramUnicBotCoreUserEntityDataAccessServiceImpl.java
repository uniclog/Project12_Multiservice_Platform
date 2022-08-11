package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.telegram.dto.TelegramUnicBotCoreUserEntityDto;
import local.uniclog.mainframe.dao.telegram.service.TelegramUnicBotCoreUserEntityDataAccessService;
import local.uniclog.mainframe.dao.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Реализация сервиса {@link TelegramUnicBotCoreUserEntityDataAccessService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUnicBotCoreUserEntityDataAccessServiceImpl implements TelegramUnicBotCoreUserEntityDataAccessService {
    public final TelegramUnicBotCoreUserEntityDataService service;

    @Override
    public <T> TelegramUnicBotCoreUserEntityDto save(T object) {
        if (object instanceof TelegramUnicBotCoreUserEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.save(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public <T> TelegramUnicBotCoreUserEntityDto update(T object) {
        if (object instanceof TelegramUnicBotCoreUserEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.update(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public TelegramUnicBotCoreUserEntityDto findByUserTelegramId(Long userTelegramId) {
        var entity = service.findByUserTelegramId(userTelegramId);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public List<TelegramUnicBotCoreUserEntityDto> findAllSubscribers() {
        var entities = service.findAllSubscribers();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TelegramUnicBotCoreUserEntityDto> findAll() {
        var entities = service.findAll();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<TelegramUnicBotCoreUserEntityDto> deleteAllByUserTelegramId(Long id) {
        var entities = service.deleteAllByUserTelegramId(id);
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }
}
