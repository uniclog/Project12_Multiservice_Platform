package local.ts3snet.unicbot_ms_spring.core.service.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramUnicBotCoreUserEntity;
import local.ts3snet.unicbot_ms_spring.core.repository.TelegramUnicBotCoreRepository;
import local.ts3snet.unicbot_ms_spring.core.service.TelegramUnicBotCoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelegramUnicBotCoreUserServiceImpl implements TelegramUnicBotCoreUserService {
    private TelegramUnicBotCoreRepository telegramUnicBotCoreRepository;
    @Autowired
    public void setTelegramTORGUserRepository(TelegramUnicBotCoreRepository telegramUnicBotCoreRepository) {
        this.telegramUnicBotCoreRepository = telegramUnicBotCoreRepository;
    }

    @Override
    public void save(TelegramUnicBotCoreUserEntity user) {
        TelegramUnicBotCoreUserEntity userFromBD = telegramUnicBotCoreRepository.findByUserTelegramId(user.getUserTelegramId());
        if(userFromBD != null) {
            update(user);
            return;
        }
        telegramUnicBotCoreRepository.save(user);
    }

    @Override
    public void update(TelegramUnicBotCoreUserEntity user) {
        TelegramUnicBotCoreUserEntity userFromDB = telegramUnicBotCoreRepository.findByUserTelegramId(user.getUserTelegramId());
        userFromDB.setSubscriber(user.getSubscriber());
        telegramUnicBotCoreRepository.save(userFromDB);
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
