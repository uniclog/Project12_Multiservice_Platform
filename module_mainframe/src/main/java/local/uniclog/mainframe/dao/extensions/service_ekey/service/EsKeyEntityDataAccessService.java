package local.uniclog.mainframe.dao.extensions.service_ekey.service;

import local.uniclog.mainframe.dao.extensions.service_ekey.dto.EsKeyEntityDto;
import local.uniclog.mainframe.dao.extensions.service_ekey.entity.EsKeyEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Обёртка для сервиса {@link EsKeyEntityDataService}
 *
 * @author uniclog
 * @version 0.1
 * @see EsKeyEntityDataService
 */
public interface EsKeyEntityDataAccessService {
    /**
     * Обёртка метода записи сущности в бд
     *
     * @param object объект пользователя
     * @param <T>    {@link Object}
     * @return {@link EsKeyEntity}
     */
    <T> EsKeyEntityDto save(T object);

    /**
     * @param object объект пользователя
     * @param <T>    {@link Object}
     * @return delete flag
     */
    <T> Boolean delete(T object);

    /**
     * Обёртка метода удаления всех записей в таблице
     */
    void deleteAll();

    /**
     * Обёртка метода удаления записи по значению ключа
     *
     * @param key значение ключа
     * @return {@link EsKeyEntityDto}
     */
    EsKeyEntityDto deleteByKey(String key);

    /**
     * Обёртка метода получения всех записей, дата которых после
     *
     * @param date дата создания ключа
     * @return List &lt;{@link EsKeyEntityDto}&gt;
     */
    List<EsKeyEntityDto> findByDateAfter(LocalDateTime date);

    /**
     * Обёртка метода получения всех записей
     *
     * @return List &lt;{@link EsKeyEntityDto}&gt;
     */
    List<EsKeyEntityDto> findAll();

    /**
     * Обёртка метода получения объекта ключа по его значению key
     *
     * @param key значение ключа
     * @return {@link EsKeyEntityDto} сущность ключей
     */
    EsKeyEntityDto findByKey(String key);
}
