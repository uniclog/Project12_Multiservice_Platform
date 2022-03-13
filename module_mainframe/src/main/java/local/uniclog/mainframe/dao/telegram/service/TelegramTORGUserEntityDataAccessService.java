package local.uniclog.mainframe.dao.telegram.service;

import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDataTransferObject;

import java.util.List;

public interface TelegramTORGUserEntityDataAccessService {
    /**
     * Сохранение сущности в бд
     *
     * @param object {@link TelegramTORGUserEntityDataTransferObject} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramTORGUserEntityDataTransferObject} сущность пользователя
     */
    <T> TelegramTORGUserEntityDataTransferObject save(T object);

    /**
     * Обновление полей для записи
     *
     * @param object {@link TelegramTORGUserEntityDataTransferObject} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramTORGUserEntityDataTransferObject} сущность пользователя
     */
    <T> TelegramTORGUserEntityDataTransferObject update(T object);

    /**
     * Возвращает запись по Telegram-Id пользователя
     *
     * @param userid Telegram-Id пользователя
     * @return {@link TelegramTORGUserEntityDataTransferObject} сущность пользователя
     */
    TelegramTORGUserEntityDataTransferObject findByUserTelegramId(Long userid);

    /**
     * Возвращает все записи с флагом subscribe
     *
     * @return List&lt;{@link TelegramTORGUserEntityDataTransferObject}&gt; список сущностей
     */
    List<TelegramTORGUserEntityDataTransferObject> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     *
     * @return List&lt;{@link TelegramTORGUserEntityDataTransferObject}&gt; список сущностей
     */
    List<TelegramTORGUserEntityDataTransferObject> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     *
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramTORGUserEntityDataTransferObject}&gt; список удаленных сущностей
     */
    List<TelegramTORGUserEntityDataTransferObject> deleteAllByUserTelegramId(Long id);
}
