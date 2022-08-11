package local.uniclog.mainframe.dao.telegram.service;

import local.uniclog.mainframe.dao.telegram.dto.TelegramUnicBotCoreUserEntityDto;

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
     * @param object {@link TelegramUnicBotCoreUserEntityDto} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramUnicBotCoreUserEntityDto} сущность пользователя
     */
    <T> TelegramUnicBotCoreUserEntityDto save(T object);

    /**
     * Обновление полей для записи
     *
     * @param object {@link TelegramUnicBotCoreUserEntityDto} объект пользователя
     * @param <T>    abstract object
     * @return {@link TelegramUnicBotCoreUserEntityDto} сущность пользователя
     */
    <T> TelegramUnicBotCoreUserEntityDto update(T object);

    /**
     * Возвращает запись по Telegram-Id пользователя
     *
     * @param userTelegramId Telegram-Id пользователя
     * @return {@link TelegramUnicBotCoreUserEntityDto} сущность пользователя
     */
    TelegramUnicBotCoreUserEntityDto findByUserTelegramId(Long userTelegramId);

    /**
     * Возвращает все записи с флагом subscribe
     *
     * @return List&lt;{@link TelegramUnicBotCoreUserEntityDto}&gt; список сущностей
     */
    List<TelegramUnicBotCoreUserEntityDto> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     *
     * @return List&lt;{@link TelegramUnicBotCoreUserEntityDto}&gt; список сущностей
     */
    List<TelegramUnicBotCoreUserEntityDto> findAll();

    /**
     * Удаление всех записей по Telegram-Id пользователя,
     * если записей нессколько удалятся все
     *
     * @param id Telegram-Id пользователя
     * @return List&lt;{@link TelegramUnicBotCoreUserEntityDto}&gt; список удаленных сущностей
     */
    List<TelegramUnicBotCoreUserEntityDto> deleteAllByUserTelegramId(Long id);
}
