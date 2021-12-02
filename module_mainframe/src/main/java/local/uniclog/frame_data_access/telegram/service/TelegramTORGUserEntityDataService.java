package local.uniclog.frame_data_access.telegram.service;

import local.uniclog.frame_data_access.telegram.entity.TelegramTORGUserEntity;

import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности {@link TelegramTORGUserEntity}
 * <li>сохранение сущности в бд {@link TelegramTORGUserEntityDataService#save(TelegramTORGUserEntity)}</li>
 * <li>обновление полей для записи {@link TelegramTORGUserEntityDataService#update(TelegramTORGUserEntity)}</li>
 * <li>возвращает запись по Telegram-Id пользователя {@link TelegramTORGUserEntityDataService#findByUserTelegramId(Long)}</li>
 * <li>возвращает все записи с флагом subscribe {@link TelegramTORGUserEntityDataService#findAllSubscribers()}</li>
 * <li>возвращает все записи из бд {@link TelegramTORGUserEntityDataService#findAll()}</li>
 * <li>удаление всех записей по Telegram-Id пользователя,
 *  если записей нессколько удалятся все {@link TelegramTORGUserEntityDataService#deleteAllByUserTelegramId(Long)} (String)}</li>
 *
 * @see TelegramTORGUserEntity
 * @author uniclog
 * @version 0.1
 */
public interface TelegramTORGUserEntityDataService {
    /**
     * Сохранение сущности в бд
     * @param user {@link TelegramTORGUserEntity} сущность пользователя
     */
    void save(TelegramTORGUserEntity user);

    /**
     * Обновление полей для записи
     * @param user {@link TelegramTORGUserEntity} сущность пользователя
     */
    void update(TelegramTORGUserEntity user);

    /**
     * Возвращает запись по Telegram-Id пользователя
     * @param userid Telegram-Id пользователя
     * @return {@link TelegramTORGUserEntity} сущность пользователя
     */
    TelegramTORGUserEntity findByUserTelegramId(Long userid);

    /**
     * Возвращает все записи с флагом subscribe
     * @return List&lt;{@link TelegramTORGUserEntity}&gt; список сущностей
     */
    List<TelegramTORGUserEntity> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     * @return List&lt;{@link TelegramTORGUserEntity}&gt; список сущностей
     */
    List<TelegramTORGUserEntity> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramTORGUserEntity}&gt; список удаленных сущностей
     */
    List<TelegramTORGUserEntity> deleteAllByUserTelegramId(Long id);
}