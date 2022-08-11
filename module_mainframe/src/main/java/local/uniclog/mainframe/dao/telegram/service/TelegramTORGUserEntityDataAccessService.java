package local.uniclog.mainframe.dao.telegram.service;

import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDto;

import java.util.List;

/**
 * Обёртка для сервиса {@link TelegramTORGUserEntityDataService}
 *
 * @author uniclog
 * @version 0.1
 * @see TelegramTORGUserEntityDataService
 */
public interface TelegramTORGUserEntityDataAccessService {
    /**
     * Сохранение сущности в бд
     *
     * @param object {@link TelegramTORGUserEntityDto} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramTORGUserEntityDto} сущность пользователя
     */
    <T> TelegramTORGUserEntityDto save(T object);

    /**
     * Обновление полей для записи
     *
     * @param object {@link TelegramTORGUserEntityDto} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramTORGUserEntityDto} сущность пользователя
     */
    <T> TelegramTORGUserEntityDto update(T object);

    /**
     * Возвращает запись по Telegram-Id пользователя
     *
     * @param userid Telegram-Id пользователя
     * @return {@link TelegramTORGUserEntityDto} сущность пользователя
     */
    TelegramTORGUserEntityDto findByUserTelegramId(Long userid);

    /**
     * Возвращает все записи с флагом subscribe
     *
     * @return List&lt;{@link TelegramTORGUserEntityDto}&gt; список сущностей
     */
    List<TelegramTORGUserEntityDto> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     *
     * @return List&lt;{@link TelegramTORGUserEntityDto}&gt; список сущностей
     */
    List<TelegramTORGUserEntityDto> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     *
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramTORGUserEntityDto}&gt; список удаленных сущностей
     */
    List<TelegramTORGUserEntityDto> deleteAllByUserTelegramId(Long id);
}
