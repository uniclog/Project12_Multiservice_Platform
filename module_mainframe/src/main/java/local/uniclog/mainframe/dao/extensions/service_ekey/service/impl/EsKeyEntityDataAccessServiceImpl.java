package local.uniclog.mainframe.dao.extensions.service_ekey.service.impl;

import local.uniclog.mainframe.dao.extensions.service_ekey.dto.EsKeyEntityDto;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataAccessService;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Реализация сервиса {@link EsKeyEntityDataAccessService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EsKeyEntityDataAccessServiceImpl implements EsKeyEntityDataAccessService {
    public final EsKeyEntityDataService service;

    @Override
    public <T> EsKeyEntityDto save(T object) {
        if (object instanceof EsKeyEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            entity = service.save(entity);
            return service.convertToDataTransferObject(entity);
        }
        return null;
    }

    @Override
    public <T> Boolean delete(T object) {
        if (object instanceof EsKeyEntityDto entityDto) {
            var entity = service.convertFromDataTransferObject(entityDto);
            service.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        service.deleteAll();
    }

    @Override
    public EsKeyEntityDto deleteByKey(String key) {
        var entity = service.deleteByKey(key);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }

    @Override
    public List<EsKeyEntityDto> findByDateAfter(LocalDateTime date) {
        var entities = service.findByDateAfter(date);
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public List<EsKeyEntityDto> findAll() {
        var entities = service.findAll();
        return (entities.isEmpty()) ? emptyList() : entities.stream()
                .map(service::convertToDataTransferObject)
                .collect(toList());
    }

    @Override
    public EsKeyEntityDto findByKey(String key) {
        var entity = service.findByKey(key);
        return (entity == null) ? null : service.convertToDataTransferObject(entity);
    }
}
