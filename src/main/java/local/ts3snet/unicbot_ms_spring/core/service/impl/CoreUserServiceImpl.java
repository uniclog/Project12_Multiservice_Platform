package local.ts3snet.unicbot_ms_spring.core.service.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.CoreUserEntity;
import local.ts3snet.unicbot_ms_spring.core.repository.CoreUserRepository;
import local.ts3snet.unicbot_ms_spring.core.service.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoreUserServiceImpl implements CoreUserService {
    private CoreUserRepository coreUserRepository;
    @Autowired
    public void setCoreUserRepository(CoreUserRepository coreUserRepository) {
        this.coreUserRepository = coreUserRepository;
    }

    @Override
    public void save(CoreUserEntity user) {
        CoreUserEntity userFromDB = coreUserRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userFromDB != null) {
            update(user);
            return;
        }
        coreUserRepository.save(user);
    }

    @Override
    public void update(CoreUserEntity user) {
            CoreUserEntity userFromDb = coreUserRepository.findByUserTelegramId(user.getUserTelegramId());
            userFromDb.setSubscriber(user.getSubscriber());
            coreUserRepository.save(userFromDb);
    }

    @Override
    public List<CoreUserEntity> findAll() {
        return coreUserRepository.findAll();
    }

    @Override
    public CoreUserEntity findByUserTelegramId(Long userTelegramId) {
        return coreUserRepository.findByUserTelegramId(userTelegramId);
    }

    @Override
    public List<CoreUserEntity> findAllSubscribers() {
        return coreUserRepository.findAllBySubscriber(true);
    }
}
