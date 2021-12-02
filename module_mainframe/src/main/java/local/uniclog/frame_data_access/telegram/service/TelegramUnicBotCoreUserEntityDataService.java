package local.uniclog.frame_data_access.telegram.service;

import local.uniclog.frame_data_access.telegram.entity.TelegramUnicBotCoreUserEntity;

import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности {@link TelegramUnicBotCoreUserEntity}
 * <li>сохранение сущности в бд {@link TelegramUnicBotCoreUserEntityDataService#save(TelegramUnicBotCoreUserEntity)}</li>
 * <li>обновление полей для записи {@link TelegramUnicBotCoreUserEntityDataService#update(TelegramUnicBotCoreUserEntity)}</li>
 * <li>возвращает запись по Telegram-Id пользователя {@link TelegramUnicBotCoreUserEntityDataService#findByUserTelegramId(Long)}</li>
 * <li>возвращает все записи с флагом subscribe {@link TelegramUnicBotCoreUserEntityDataService#findAllSubscribers()}</li>
 * <li>возвращает все записи из бд {@link TelegramUnicBotCoreUserEntityDataService#findAll()}</li>
 * <li>удаление всех записей по Telegram-Id пользователя,
 *  если записей нессколько удалятся все {@link TelegramUnicBotCoreUserEntityDataService#deleteAllByUserTelegramId(Long)} (String)}</li>
 *
 * @see TelegramUnicBotCoreUserEntity
 * @author uniclog
 * @version 0.1
 */
public interface TelegramUnicBotCoreUserEntityDataService {
    /**
     * Сохранение сущности в бд
     * @param user {@link TelegramUnicBotCoreUserEntity} сущность пользователя
     */
    void save(TelegramUnicBotCoreUserEntity user);

    /**
     * Обновление полей для записи
     * @param user {@link TelegramUnicBotCoreUserEntity} сущность пользователя
     */
    void update(TelegramUnicBotCoreUserEntity user);

    /**
     * Возвращает запись по Telegram-Id пользователя
     * @param userTelegramId Telegram-Id пользователя
     * @return {@link TelegramUnicBotCoreUserEntity} сущность пользователя
     */
    TelegramUnicBotCoreUserEntity findByUserTelegramId(Long userTelegramId);

    /**
     * Возвращает все записи с флагом subscribe
     * @return List&lt;{@link TelegramUnicBotCoreUserEntity}&gt; список сущностей
     */
    List<TelegramUnicBotCoreUserEntity> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     * @return List&lt;{@link TelegramUnicBotCoreUserEntity}&gt; список сущностей
     */
    List<TelegramUnicBotCoreUserEntity> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramUnicBotCoreUserEntity}&gt; список удаленных сущностей
     */
    List<TelegramUnicBotCoreUserEntity> deleteAllByUserTelegramId(Long id);
}
