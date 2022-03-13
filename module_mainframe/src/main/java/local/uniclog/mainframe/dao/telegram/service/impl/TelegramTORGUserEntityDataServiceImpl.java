package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.common.DataUtilsService;
import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.entity.TelegramTORGUserEntity;
import local.uniclog.mainframe.dao.telegram.repository.TelegramTORGUserRepository;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса {@link TelegramTORGUserEntityDataService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramTORGUserEntityDataServiceImpl implements TelegramTORGUserEntityDataService {
    private final TelegramTORGUserRepository telegramTORGUserRepository;
    private final DataUtilsService dataUtilsService;

    @Override
    public <T> TelegramTORGUserEntity save(T object) {
        if (object instanceof TelegramTORGUserEntity entity) {
            var entityByToken = this.findByUserTelegramId(entity.getUserTelegramId());
            if (entityByToken != null) {
                entity.setId(entityByToken.getId());
            }
            return telegramTORGUserRepository.save(entity);
        }
        return null;
    }

    @Override
    public <T> TelegramTORGUserEntity update(T object) {
        return this.save(object);
    }

    @Override
    public List<TelegramTORGUserEntity> findAll() {
        return telegramTORGUserRepository.findAll();
    }

    @Override
    public TelegramTORGUserEntity findByUserTelegramId(Long userTelegramId) {
        try {
            return telegramTORGUserRepository.findAllByUserTelegramId(userTelegramId).get(0);
        } catch (IndexOutOfBoundsException exception) {
            log.warn("User not found. userTelegramId={}", userTelegramId);
            return null;
        }
    }

    @Override
    public List<TelegramTORGUserEntity> findAllSubscribers() {
        return telegramTORGUserRepository.findAllBySubscriber(true);
    }

    @Override
    public List<TelegramTORGUserEntity> deleteAllByUserTelegramId(Long id) {
        List<TelegramTORGUserEntity> users = telegramTORGUserRepository.findAllByUserTelegramId(id);
        if (users.isEmpty()) return Collections.emptyList();
        telegramTORGUserRepository.deleteAllByUserTelegramId(id);
        return users;
    }

    @Override
    public TelegramTORGUserEntityDataTransferObject convertToDataTransferObject(TelegramTORGUserEntity entity) {
        return dataUtilsService.convertData(entity, TelegramTORGUserEntityDataTransferObject.class);
    }

    @Override
    public TelegramTORGUserEntity convertFromDataTransferObject(TelegramTORGUserEntityDataTransferObject dto) {
        return dataUtilsService.convertData(dto, TelegramTORGUserEntity.class);
    }
}
