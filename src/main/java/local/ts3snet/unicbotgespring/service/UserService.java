package local.ts3snet.unicbotgespring.service;

import local.ts3snet.unicbotgespring.entity.UserEntity;

import java.util.List;

public interface UserService {
    void save(UserEntity user);
    void update(UserEntity user);
    UserEntity findByUserTelegramId(Long userid);
    List<UserEntity> findAllSubscribers();
}