package local.ts3snet.unicbot_ms_spring.core.service.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TelegramMyFitnessUserEntity;
import local.ts3snet.unicbot_ms_spring.core.repository.TelegramMyFitnessUserRepository;
import local.ts3snet.unicbot_ms_spring.core.service.TelegramMyFitnessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelegramMyFitnessUserServiceImpl implements TelegramMyFitnessUserService {
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
        user.setId(userEntity.getId());
        telegramMyFitnessUserRepository.save(user);
    }

    @Override
    public TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId) {
        return telegramMyFitnessUserRepository.findByUserTelegramId(userTelegramId);
    }

    @Override
    public List<TelegramMyFitnessUserEntity> findAllSubscribers() {
        return telegramMyFitnessUserRepository.findBySubscriber(true);
    }

    @Override
    public List<TelegramMyFitnessUserEntity> findAll() {
        return telegramMyFitnessUserRepository.findAll();
    }
}
