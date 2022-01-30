package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.common.DataUtilsService;
import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.entity.TelegramMyFitnessUserEntity;
import local.uniclog.mainframe.dao.telegram.repository.TelegramMyFitnessUserRepository;
import local.uniclog.mainframe.dao.telegram.service.TelegramMyFitnessUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса {@link TelegramMyFitnessUserEntityDataService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramMyFitnessUserEntityDataServiceImpl implements TelegramMyFitnessUserEntityDataService {
    private final TelegramMyFitnessUserRepository telegramMyFitnessUserRepository;
    private final DataUtilsService dataUtilsService;

    @Override
    public TelegramMyFitnessUserEntity save(TelegramMyFitnessUserEntity user) {
        TelegramMyFitnessUserEntity userEntity = telegramMyFitnessUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userEntity != null) {
            return update(user);
        }
        return telegramMyFitnessUserRepository.save(user);
    }

    @Override
    public TelegramMyFitnessUserEntity update(TelegramMyFitnessUserEntity user) {
        TelegramMyFitnessUserEntity userEntity = telegramMyFitnessUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userEntity == null) {
            return save(user);
        }
        user.setId(userEntity.getId());
        return telegramMyFitnessUserRepository.save(user);
    }

    @Override
    public TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId) {
        try {
            return telegramMyFitnessUserRepository.findAllByUserTelegramId(userTelegramId).get(0);
        } catch (IndexOutOfBoundsException exception) {
            log.warn("User not found. userTelegramId={}", userTelegramId);
            return null;
        }
    }

    @Override
    public List<TelegramMyFitnessUserEntity> findAllSubscribers() {
        return telegramMyFitnessUserRepository.findBySubscriber(true);
    }

    @Override
    public List<TelegramMyFitnessUserEntity> findAll() {
        return telegramMyFitnessUserRepository.findAll();
    }

    @Override
    public List<TelegramMyFitnessUserEntity> deleteAllByUserTelegramId(Long id) {
        List<TelegramMyFitnessUserEntity> users = telegramMyFitnessUserRepository.findAllByUserTelegramId(id);
        if (users.isEmpty()) return Collections.emptyList();
        telegramMyFitnessUserRepository.deleteAllByUserTelegramId(id);
        return users;
    }

    @Override
    public TelegramMyFitnessUserEntityDataTransferObject convertToDataTransferObject(TelegramMyFitnessUserEntity entity) {
        return dataUtilsService.convertToDataTransferObject(entity, TelegramMyFitnessUserEntityDataTransferObject.class);
    }

    @Override
    public TelegramMyFitnessUserEntity convertFromDataTransferObject(TelegramMyFitnessUserEntityDataTransferObject dto) {
        return dataUtilsService.convertFromDataTransferObject(dto, TelegramMyFitnessUserEntity.class);
    }
}
