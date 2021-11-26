package local.uniclog.frame_data_access.telegram.service.impl;

import local.uniclog.frame_data_access.telegram.entity.TelegramTORGUserEntity;
import local.uniclog.frame_data_access.telegram.repository.TelegramTORGUserRepository;
import local.uniclog.frame_data_access.telegram.service.TelegramTORGUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramTORGUserEntityDataServiceImpl implements TelegramTORGUserEntityDataService {
    private final TelegramTORGUserRepository telegramTORGUserRepository;

    @Override
    public void save(TelegramTORGUserEntity user) {
        TelegramTORGUserEntity userEntity = telegramTORGUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userEntity != null) {
            update(user);
            return;
        }
        telegramTORGUserRepository.save(user);
    }

    @Override
    public void update(TelegramTORGUserEntity user) {
        TelegramTORGUserEntity userFromDb = telegramTORGUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userFromDb == null) {
            save(user);
            return;
        }
        user.setId(userFromDb.getId());
        telegramTORGUserRepository.save(user);
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
        if (users.isEmpty()) return null;
        telegramTORGUserRepository.deleteAllByUserTelegramId(id);
        return users;
    }
}
