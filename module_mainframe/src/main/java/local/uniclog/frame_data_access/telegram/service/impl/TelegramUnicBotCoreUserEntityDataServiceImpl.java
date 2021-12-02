package local.uniclog.frame_data_access.telegram.service.impl;

import local.uniclog.frame_data_access.telegram.entity.TelegramUnicBotCoreUserEntity;
import local.uniclog.frame_data_access.telegram.repository.TelegramUnicBotCoreRepository;
import local.uniclog.frame_data_access.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса {@link TelegramUnicBotCoreUserEntityDataService}
 * @version 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUnicBotCoreUserEntityDataServiceImpl implements TelegramUnicBotCoreUserEntityDataService {
    private final TelegramUnicBotCoreRepository telegramUnicBotCoreRepository;

    @Override
    public void save(TelegramUnicBotCoreUserEntity user) {
        TelegramUnicBotCoreUserEntity userEntity = telegramUnicBotCoreRepository.findByUserTelegramId(user.getUserTelegramId());
        if(userEntity != null) {
            update(user);
            return;
        }
        telegramUnicBotCoreRepository.save(user);
    }

    @Override
    public void update(TelegramUnicBotCoreUserEntity user) {
        TelegramUnicBotCoreUserEntity userFromDB = telegramUnicBotCoreRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userFromDB == null) {
            save(user);
            return;
        }
        user.setId(userFromDB.getId());
        telegramUnicBotCoreRepository.save(user);
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
}
