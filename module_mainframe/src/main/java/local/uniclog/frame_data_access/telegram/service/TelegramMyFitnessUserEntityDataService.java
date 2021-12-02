package local.uniclog.frame_data_access.telegram.service;

import local.uniclog.frame_data_access.telegram.entity.TelegramMyFitnessUserEntity;

import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности {@link TelegramMyFitnessUserEntity}
 * <ul>
 * <li>сохранение сущности в бд {@link TelegramMyFitnessUserEntityDataService#save(TelegramMyFitnessUserEntity)}</li>
 * <li>обновление полей для записи {@link TelegramMyFitnessUserEntityDataService#update(TelegramMyFitnessUserEntity)}</li>
 * <li>возвращает запись по Telegram-Id пользователя {@link TelegramMyFitnessUserEntityDataService#findByUserTelegramId(Long)}</li>
 * <li>возвращает все записи с флагом subscribe {@link TelegramMyFitnessUserEntityDataService#findAllSubscribers()}</li>
 * <li>возвращает все записи из бд {@link TelegramMyFitnessUserEntityDataService#findAll()}</li>
 * <li>удаление всех записей по Telegram-Id пользователя,
 *  если записей нессколько удалятся все {@link TelegramMyFitnessUserEntityDataService#deleteAllByUserTelegramId(Long)} (String)}</li>
 * </ul>
 *
 * @author uniclog
 * @version 0.1
 * @see TelegramMyFitnessUserEntity
 */
public interface TelegramMyFitnessUserEntityDataService {
    /**
     * Сохранение сущности в бд
     *
     * @param user {@link TelegramMyFitnessUserEntity} сущность пользователя
     */
    void save(TelegramMyFitnessUserEntity user);

    /**
     * Обновление полей для записи
     *
     * @param user {@link TelegramMyFitnessUserEntity} сущность пользователя
     */
    void update(TelegramMyFitnessUserEntity user);

    /**
     * Возвращает запись по Telegram-Id пользователя
     *
     * @param userTelegramId Telegram-Id пользователя
     * @return {@link TelegramMyFitnessUserEntity} сущность пользователя
     */
    TelegramMyFitnessUserEntity findByUserTelegramId(Long userTelegramId);

    /**
     * Возвращает все записи с флагом subscribe
     *
     * @return List&lt;{@link TelegramMyFitnessUserEntity}&gt; список сущностей
     */
    List<TelegramMyFitnessUserEntity> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     *
     * @return List&lt;{@link TelegramMyFitnessUserEntity}&gt; список сущностей
     */
    List<TelegramMyFitnessUserEntity> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     *
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramMyFitnessUserEntity}&gt; список удаленных сущностей
     */
    List<TelegramMyFitnessUserEntity> deleteAllByUserTelegramId(Long id);
}
