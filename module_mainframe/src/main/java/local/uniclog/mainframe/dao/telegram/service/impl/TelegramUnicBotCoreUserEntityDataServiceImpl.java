package local.uniclog.mainframe.dao.telegram.service.impl;

import local.uniclog.mainframe.dao.common.DataUtilsService;
import local.uniclog.mainframe.dao.telegram.dto.TelegramUnicBotCoreUserEntityDto;
import local.uniclog.mainframe.dao.telegram.entity.TelegramUnicBotCoreUserEntity;
import local.uniclog.mainframe.dao.telegram.repository.TelegramUnicBotCoreRepository;
import local.uniclog.mainframe.dao.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса {@link TelegramUnicBotCoreUserEntityDataService}
 *
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUnicBotCoreUserEntityDataServiceImpl implements TelegramUnicBotCoreUserEntityDataService {
    private final TelegramUnicBotCoreRepository telegramUnicBotCoreRepository;
    private final DataUtilsService dataUtilsService;

    @Override
    public <T> TelegramUnicBotCoreUserEntity save(T object) {
        if (object instanceof TelegramUnicBotCoreUserEntity entity) {
            var entityByToken = this.findByUserTelegramId(entity.getUserTelegramId());
            if (entityByToken != null) {
                entity.setId(entityByToken.getId());
            }
            return telegramUnicBotCoreRepository.save(entity);
        }
        return null;
    }

    @Override
    public <T> TelegramUnicBotCoreUserEntity update(T object) {
        return this.save(object);
    }

    @Override
    public TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId) {
        try {
            return telegramUnicBotCoreRepository.findAllByUserTelegramId(userTelegramId).get(0);
        } catch (IndexOutOfBoundsException exception) {
            log.warn("User not found. userTelegramId={}", userTelegramId);
            return null;
        }
    }

    @Override
    public List<TelegramUnicBotCoreUserEntity> findAllSubscribers() {
        return telegramUnicBotCoreRepository.findBySubscriber(true);
    }

    @Override
    public List<TelegramUnicBotCoreUserEntity> findAll() {
        return telegramUnicBotCoreRepository.findAll();
    }

    @Override
    public List<TelegramUnicBotCoreUserEntity> deleteAllByUserTelegramId(Long id) {
        List<TelegramUnicBotCoreUserEntity> users = telegramUnicBotCoreRepository.findAllByUserTelegramId(id);
        if (users.isEmpty()) return Collections.emptyList();
        telegramUnicBotCoreRepository.deleteAllByUserTelegramId(id);
        return users;
    }

    @Override
    public TelegramUnicBotCoreUserEntityDto convertToDataTransferObject(TelegramUnicBotCoreUserEntity entity) {
        return dataUtilsService.convertData(entity, TelegramUnicBotCoreUserEntityDto.class);
    }

    @Override
    public TelegramUnicBotCoreUserEntity convertFromDataTransferObject(TelegramUnicBotCoreUserEntityDto dto) {
        return dataUtilsService.convertData(dto, TelegramUnicBotCoreUserEntity.class);
    }
}
