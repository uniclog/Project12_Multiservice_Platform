package local.ts3snet.unicbotgespring.service.impl;

import local.ts3snet.unicbotgespring.entity.UserEntity;
import local.ts3snet.unicbotgespring.repository.UserRepository;
import local.ts3snet.unicbotgespring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserEntity user) {
        UserEntity userFromDB = userRepository.findByUserTelegramId(user.getUserTelegramId());
        if (userFromDB != null) {
            update(user);
            return;
        }
        userRepository.save(user);
    }

    @Override
    public void update(UserEntity user) {
            UserEntity userFromDb = userRepository.findByUserTelegramId(user.getUserTelegramId());
            userFromDb.setSubscriber(user.getSubscriber());
            userRepository.save(userFromDb);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findByUserTelegramId(Long userTelegramId) {
        return userRepository.findByUserTelegramId(userTelegramId);
    }

    @Override
    public List<UserEntity> findAllSubscribers() {
        return userRepository.findAllBySubscriber(true);
    }
}
