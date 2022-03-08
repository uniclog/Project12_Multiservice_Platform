package local.uniclog.mainframe.dao.telegram.service;

import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.entity.TelegramMyFitnessUserEntity;

import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности {@link TelegramMyFitnessUserEntity}
 * <ul>
 * <li>сохранение сущности в бд {@link TelegramMyFitnessUserEntityDataService#save(Object)}</li>
 * <li>обновление полей для записи {@link TelegramMyFitnessUserEntityDataService#update(Object)}</li>
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
     * @param object {@link TelegramMyFitnessUserEntity} сущность пользователя
     * @param <T>    объект для сохранения
     * @return {@link TelegramMyFitnessUserEntity} сущность пользователя
     */
    <T> TelegramMyFitnessUserEntity save(T object);


    /**
     * Обновление полей для записи
     *
     * @param object {@link TelegramMyFitnessUserEntity} сущность пользователя
     * @param <T>    объект для обновления
     * @return {@link TelegramMyFitnessUserEntity} сущность пользователя
     */
    <T> TelegramMyFitnessUserEntity update(T object);

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

    /**
     * Convert entity to Dto object
     *
     * @param entity {@link TelegramMyFitnessUserEntity}
     * @return {@link TelegramMyFitnessUserEntityDataTransferObject}
     */
    TelegramMyFitnessUserEntityDataTransferObject convertToDataTransferObject(TelegramMyFitnessUserEntity entity);

    /**
     * Convert Dto object to entity
     *
     * @param dto {@link TelegramMyFitnessUserEntityDataTransferObject}
     * @return {@link TelegramMyFitnessUserEntity}
     */
    TelegramMyFitnessUserEntity convertFromDataTransferObject(TelegramMyFitnessUserEntityDataTransferObject dto);
}
