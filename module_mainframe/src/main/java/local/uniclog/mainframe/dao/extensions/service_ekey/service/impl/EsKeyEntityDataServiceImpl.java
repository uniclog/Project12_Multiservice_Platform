package local.uniclog.mainframe.dao.extensions.service_ekey.service.impl;

import local.uniclog.mainframe.dao.common.DataUtilsService;
import local.uniclog.mainframe.dao.extensions.service_ekey.dto.EsKeyEntityDto;
import local.uniclog.mainframe.dao.extensions.service_ekey.entity.EsKeyEntity;
import local.uniclog.mainframe.dao.extensions.service_ekey.repository.EsKeyRepository;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDto;
import local.uniclog.mainframe.dao.telegram.entity.TelegramUnicBotCoreUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация сервиса {@link EsKeyEntityDataService}
 * @version 0.1
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EsKeyEntityDataServiceImpl implements EsKeyEntityDataService {
    private final DataUtilsService dataUtilsService;
    private final EsKeyRepository repository;

    @Override
    public EsKeyEntity save(EsKeyEntity key) {
        return repository.save(key);
    }

    @Override
    public void delete(EsKeyEntity key) {
        repository.delete(key);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public EsKeyEntity deleteByKey(String key) {
        EsKeyEntity entity = repository.findByKeyValue(key);
        if (entity == null) return null;
        this.delete(entity);
        return entity;
    }

    @Override
    public List<EsKeyEntity> findByDateAfter(LocalDateTime date) {
        return repository.findByDateAfter(date);
    }

    @Override
    public List<EsKeyEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public EsKeyEntity findByKey(String key) {
        return repository.findByKeyValue(key);
    }

    @Override
    public EsKeyEntityDto convertToDataTransferObject(EsKeyEntity entity) {
        return dataUtilsService.convertData(entity, TelegramMyFitnessUserEntityDto.class);
    }

    @Override
    public EsKeyEntity convertFromDataTransferObject(EsKeyEntityDto dto) {
        return dataUtilsService.convertData(dto, TelegramUnicBotCoreUserEntity.class);
    }
}
