package local.uniclog.frame_dataaccess.service.impl;

import local.uniclog.frame_dataaccess.entity.TelegramMyFitnessUserEntity;
import local.uniclog.frame_dataaccess.repository.TelegramMyFitnessUserRepository;
import local.uniclog.frame_dataaccess.service.TelegramMyFitnessUserService;
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
