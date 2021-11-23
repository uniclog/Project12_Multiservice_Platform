package local.uniclog.frame_data_access.service.impl;

import local.uniclog.frame_data_access.entity.TelegramTORGUserEntity;
import local.uniclog.frame_data_access.repository.TelegramTORGUserRepository;
import local.uniclog.frame_data_access.service.TelegramTORGUserEntityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelegramTORGUserEntityDataServiceImpl implements TelegramTORGUserEntityDataService {
    private TelegramTORGUserRepository telegramTORGUserRepository;
    @Autowired
    public void setTelegramTORGUserRepository(TelegramTORGUserRepository telegramTORGUserRepository) {
        this.telegramTORGUserRepository = telegramTORGUserRepository;
    }

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
            telegramTORGUserRepository.save(user);
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
