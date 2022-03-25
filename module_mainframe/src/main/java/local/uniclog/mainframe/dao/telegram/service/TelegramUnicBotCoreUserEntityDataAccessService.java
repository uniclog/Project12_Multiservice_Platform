package local.uniclog.mainframe.dao.telegram.service;

import local.uniclog.mainframe.dao.telegram.dto.TelegramUnicBotCoreUserEntityDataTransferObject;

import java.util.List;

/**
 * Обёртка для сервиса {@link TelegramUnicBotCoreUserEntityDataService}
 *
 * @author uniclog
 * @version 0.1
 * @see TelegramUnicBotCoreUserEntityDataService
 */
public interface TelegramUnicBotCoreUserEntityDataAccessService {
    /**
     * Сохранение сущности в бд
     *
     * @param object {@link TelegramUnicBotCoreUserEntityDataTransferObject} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramUnicBotCoreUserEntityDataTransferObject} сущность пользователя
     */
    <T> TelegramUnicBotCoreUserEntityDataTransferObject save(T object);

    /**
     * Обновление полей для записи
     *
     * @param object {@link TelegramUnicBotCoreUserEntityDataTransferObject} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramUnicBotCoreUserEntityDataTransferObject} сущность пользователя
     */
    <T> TelegramUnicBotCoreUserEntityDataTransferObject update(T object);

    /**
     * Возвращает запись по Telegram-Id пользователя
     *
     * @param userTelegramId Telegram-Id пользователя
     * @return {@link TelegramUnicBotCoreUserEntityDataTransferObject} сущность пользователя
     */
    TelegramUnicBotCoreUserEntityDataTransferObject findByUserTelegramId(Long userTelegramId);

    /**
     * Возвращает все записи с флагом subscribe
     *
     * @return List&lt;{@link TelegramUnicBotCoreUserEntityDataTransferObject}&gt; список сущностей
     */
    List<TelegramUnicBotCoreUserEntityDataTransferObject> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     *
     * @return List&lt;{@link TelegramUnicBotCoreUserEntityDataTransferObject}&gt; список сущностей
     */
    List<TelegramUnicBotCoreUserEntityDataTransferObject> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     *
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramUnicBotCoreUserEntityDataTransferObject}&gt; список удаленных сущностей
     */
    List<TelegramUnicBotCoreUserEntityDataTransferObject> deleteAllByUserTelegramId(Long id);
}
