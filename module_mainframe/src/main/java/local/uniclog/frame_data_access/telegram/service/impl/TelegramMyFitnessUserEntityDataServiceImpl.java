package local.uniclog.frame_data_access.telegram.service.impl;

import local.uniclog.frame_data_access.telegram.entity.TelegramMyFitnessUserEntity;
import local.uniclog.frame_data_access.telegram.repository.TelegramMyFitnessUserRepository;
import local.uniclog.frame_data_access.telegram.service.TelegramMyFitnessUserEntityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramMyFitnessUserEntityDataServiceImpl implements TelegramMyFitnessUserEntityDataService {
    private final TelegramMyFitnessUserRepository telegramMyFitnessUserRepository;

    @Override
    public void save(TelegramMyFitnessUserEntity user) {
        TelegramMyFitnessUserEntity userEntity = telegramMyFitnessUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if(userEntity != null) {
            update(user);
            return;
        }
        telegramMyFitnessUserRepository.save(user);
    }

    @Override
    public void update(TelegramMyFitnessUserEntity user) {
        TelegramMyFitnessUserEntity userEntity = telegramMyFitnessUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userEntity == null) {
            save(user);
            return;
        }
        user.setId(userEntity.getId());
        telegramMyFitnessUserRepository.save(user);
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
        if (users.isEmpty()) return null;
        telegramMyFitnessUserRepository.deleteAllByUserTelegramId(id);
        return users;
    }
}
