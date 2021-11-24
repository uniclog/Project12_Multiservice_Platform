package local.uniclog.frame_data_access.telegram.service.impl;

import local.uniclog.frame_data_access.telegram.entity.TelegramMyFitnessUserEntity;
import local.uniclog.frame_data_access.telegram.repository.TelegramMyFitnessUserRepository;
import local.uniclog.frame_data_access.telegram.service.TelegramMyFitnessUserEntityDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TelegramMyFitnessUserEntityDataServiceImpl implements TelegramMyFitnessUserEntityDataService {
    private TelegramMyFitnessUserRepository telegramMyFitnessUserRepository;
    @Autowired
    public void setTelegramTORGUserRepository(TelegramMyFitnessUserRepository telegramMyFitnessUserRepository) {
        this.telegramMyFitnessUserRepository = telegramMyFitnessUserRepository;
    }

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
