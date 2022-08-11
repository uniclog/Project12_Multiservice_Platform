package local.uniclog.mainframe.dao.telegram.service;

import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDto;
import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDto;
import local.uniclog.mainframe.dao.telegram.entity.TelegramTORGUserEntity;

import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности {@link TelegramTORGUserEntity}
 * <ul>
 * <li>сохранение сущности в бд {@link TelegramTORGUserEntityDataService#save(Object)}</li>
 * <li>обновление полей для записи {@link TelegramTORGUserEntityDataService#update(Object)}</li>
 * <li>возвращает запись по Telegram-Id пользователя {@link TelegramTORGUserEntityDataService#findByUserTelegramId(Long)}</li>
 * <li>возвращает все записи с флагом subscribe {@link TelegramTORGUserEntityDataService#findAllSubscribers()}</li>
 * <li>возвращает все записи из бд {@link TelegramTORGUserEntityDataService#findAll()}</li>
 * <li>удаление всех записей по Telegram-Id пользователя,
 *  если записей нессколько удалятся все {@link TelegramTORGUserEntityDataService#deleteAllByUserTelegramId(Long)} (String)}</li>
 * </ul>
 *
 * @author uniclog
 * @version 0.1
 * @see TelegramTORGUserEntity
 */
public interface TelegramTORGUserEntityDataService {
    /**
     * Сохранение сущности в бд
     *
     * @param object {@link TelegramTORGUserEntity} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramTORGUserEntity} сущность пользователя
     */
    <T> TelegramTORGUserEntity save(T object);

    /**
     * Обновление полей для записи
     *
     * @param object {@link TelegramTORGUserEntity} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramTORGUserEntity} сущность пользователя
     */
    <T> TelegramTORGUserEntity update(T object);

    /**
     * Возвращает запись по Telegram-Id пользователя
     *
     * @param userid Telegram-Id пользователя
     * @return {@link TelegramTORGUserEntity} сущность пользователя
     */
    TelegramTORGUserEntity findByUserTelegramId(Long userid);

    /**
     * Возвращает все записи с флагом subscribe
     *
     * @return List&lt;{@link TelegramTORGUserEntity}&gt; список сущностей
     */
    List<TelegramTORGUserEntity> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     *
     * @return List&lt;{@link TelegramTORGUserEntity}&gt; список сущностей
     */
    List<TelegramTORGUserEntity> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     *
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramTORGUserEntity}&gt; список удаленных сущностей
     */
    List<TelegramTORGUserEntity> deleteAllByUserTelegramId(Long id);

    /**
     * Convert entity to Dto object
     *
     * @param entity {@link TelegramTORGUserEntity}
     * @return {@link TelegramMyFitnessUserEntityDto}
     */
    TelegramTORGUserEntityDto convertToDataTransferObject(TelegramTORGUserEntity entity);

    /**
     * Convert Dto object to entity
     *
     * @param dto {@link TelegramMyFitnessUserEntityDto}
     * @return {@link TelegramTORGUserEntity}
     */
    TelegramTORGUserEntity convertFromDataTransferObject(TelegramTORGUserEntityDto dto);
}