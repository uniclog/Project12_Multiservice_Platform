package local.uniclog.frame_data_access.service.impl;

import local.uniclog.frame_data_access.entity.TelegramUnicBotCoreUserEntity;
import local.uniclog.frame_data_access.repository.TelegramUnicBotCoreRepository;
import local.uniclog.frame_data_access.service.TelegramUnicBotCoreUserEntityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelegramUnicBotCoreUserEntityDataServiceImpl implements TelegramUnicBotCoreUserEntityDataService {
    private TelegramUnicBotCoreRepository telegramUnicBotCoreRepository;
    @Autowired
    public void setTelegramTORGUserRepository(TelegramUnicBotCoreRepository telegramUnicBotCoreRepository) {
        this.telegramUnicBotCoreRepository = telegramUnicBotCoreRepository;
    }

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
        telegramUnicBotCoreRepository.save(user);
    }

    @Override
    public TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId) {
        return telegramUnicBotCoreRepository.findByUserTelegramId(userTelegramId);
    }

    @Override
    public List<TelegramUnicBotCoreUserEntity> findAllSubscribers() {
        return telegramUnicBotCoreRepository.findBySubscriber(true);
    }

    @Override
    public List<TelegramUnicBotCoreUserEntity> findAll() {
        return telegramUnicBotCoreRepository.findAll();
    }
}
