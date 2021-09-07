package local.ts3snet.unicbot_ms_spring.core.service.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramTORGUserEntity;
import local.ts3snet.unicbot_ms_spring.core.repository.TelegramTORGUserRepository;
import local.ts3snet.unicbot_ms_spring.core.service.TelegramTORGUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelegramTORGUserServiceImpl implements TelegramTORGUserService {
    private TelegramTORGUserRepository telegramTORGUserRepository;
    @Autowired
    public void setTelegramTORGUserRepository(TelegramTORGUserRepository telegramTORGUserRepository) {
        this.telegramTORGUserRepository = telegramTORGUserRepository;
    }

    @Override
    public void save(TelegramTORGUserEntity user) {
        TelegramTORGUserEntity userFromDB = telegramTORGUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userFromDB != null) {
            update(user);
            return;
        }
        telegramTORGUserRepository.save(user);
    }

    @Override
    public void update(TelegramTORGUserEntity user) {
            TelegramTORGUserEntity userFromDb = telegramTORGUserRepository.findByUserTelegramId(user.getUserTelegramId());
            userFromDb.setSubscriber(user.getSubscriber());
            telegramTORGUserRepository.save(userFromDb);
    }

    @Override
    public List<TelegramTORGUserEntity> findAll() {
        return telegramTORGUserRepository.findAll();
    }

    @Override
    public TelegramTORGUserEntity findByUserTelegramId(Long userTelegramId) {
        return telegramTORGUserRepository.findByUserTelegramId(userTelegramId);
    }

    @Override
    public List<TelegramTORGUserEntity> findAllSubscribers() {
        return telegramTORGUserRepository.findAllBySubscriber(true);
    }
}
