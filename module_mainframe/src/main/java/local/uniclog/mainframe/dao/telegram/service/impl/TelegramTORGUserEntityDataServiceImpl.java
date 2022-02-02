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
    public TelegramTORGUserEntity save(TelegramTORGUserEntity user) {
        TelegramTORGUserEntity userEntity = telegramTORGUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userEntity != null) {
            return update(user);
        }
        return telegramTORGUserRepository.save(user);
    }

    @Override
    public TelegramTORGUserEntity update(TelegramTORGUserEntity user) {
        TelegramTORGUserEntity userFromDb = telegramTORGUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userFromDb == null) {
            return save(user);
        }
        user.setId(userFromDb.getId());
        return telegramTORGUserRepository.save(user);
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
        return dataUtilsService.convertToDataTransferObject(entity, TelegramTORGUserEntityDataTransferObject.class);
    }

    @Override
    public TelegramTORGUserEntity convertFromDataTransferObject(TelegramTORGUserEntityDataTransferObject dto) {
        return dataUtilsService.convertFromDataTransferObject(dto, TelegramTORGUserEntity.class);
    }
}
