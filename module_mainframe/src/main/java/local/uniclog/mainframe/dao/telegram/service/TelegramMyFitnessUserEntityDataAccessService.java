package local.uniclog.mainframe.dao.telegram.service;

import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDataTransferObject;

import java.util.List;

/**
 * Обёртка для сервиса {@link TelegramMyFitnessUserEntityDataService}
 *
 * @author uniclog
 * @version 0.1
 * @see TelegramMyFitnessUserEntityDataService
 */
public interface TelegramMyFitnessUserEntityDataAccessService {
    /**
     * Обёртка метода записи сущности в бд
     *
     * @param object {@link Object}
     * @param <T>    объект пользователя
     * @return {@link TelegramMyFitnessUserEntityDataTransferObject}
     */
    <T> TelegramMyFitnessUserEntityDataTransferObject save(T object);

    /**
     * Обёртка метода обновления записи
     *
     * @param object {@link Object}
     * @param <T>    объект пользователя
     * @return {@link TelegramMyFitnessUserEntityDataTransferObject}
     */
    <T> TelegramMyFitnessUserEntityDataTransferObject update(T object);

    /**
     * Обертка для получения {@link TelegramMyFitnessUserEntityDataTransferObject} по Telegram-Id
     *
     * @param userTelegramId Telegram-Id пользователя
     * @return {@link TelegramMyFitnessUserEntityDataTransferObject}
     */
    TelegramMyFitnessUserEntityDataTransferObject findByUserTelegramId(Long userTelegramId);

    /**
     * Обёртка метода возвращения всех записей с флагом subscribe
     *
     * @return List&lt;{@link TelegramMyFitnessUserEntityDataTransferObject}&gt; список Dto
     */
    List<TelegramMyFitnessUserEntityDataTransferObject> findAllSubscribers();

    /**
     * Обёртка метода возвращения всех записей из бд
     *
     * @return List&lt;{@link TelegramMyFitnessUserEntityDataTransferObject}&gt; список Dto
     */
    List<TelegramMyFitnessUserEntityDataTransferObject> findAll();

    /**
     * Обёртка метода удаления всех записей по Telegram-Id пользователя,
     * если записей несколько удалятся все
     *
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramMyFitnessUserEntityDataTransferObject}&gt; список удаленных сущностей в Dto
     */
    List<TelegramMyFitnessUserEntityDataTransferObject> deleteAllByUserTelegramId(Long id);
}
