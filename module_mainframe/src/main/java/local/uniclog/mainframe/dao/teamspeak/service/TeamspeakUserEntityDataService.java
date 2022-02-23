package local.uniclog.mainframe.dao.teamspeak.service;

import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.teamspeak.entity.TeamspeakUserEntity;

import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности {@link TeamspeakUserEntity}
 * <ul>
 * <li>сохранение сущности в бд {@link TeamspeakUserEntityDataService#save(Object)}</li>
 * <li>обновление полей для записи {@link TeamspeakUserEntityDataService#update(Object)}</li>
 * <li>возвращает запись по token {@link TeamspeakUserEntityDataService#findByTeamspeakToken(String)}</li>
 * <li>возвращает все записи с флагом subscribe {@link TeamspeakUserEntityDataService#findAllSubscribers()}</li>
 * <li>возвращает все записи из бд {@link TeamspeakUserEntityDataService#findAll()}</li>
 * <li>удаление записи по token пользователя {@link TeamspeakUserEntityDataService#deleteByTeamspeakToken(String)}</li>
 * </ul>
 *
 * @author uniclog
 * @version 0.1
 * @see TeamspeakUserEntity
 */
public interface TeamspeakUserEntityDataService {
    /**
     * Сохранение сущности в бд
     *
     * @param user {@link TeamspeakUserEntity} объект для обновления / сущность пользователя
     * @param <T>  объект для сохранения
     * @return {@link TeamspeakUserEntity} сущность пользователя
     */
    <T> TeamspeakUserEntity save(T user);

    /**
     * Обновление полей для записи
     *
     * @param user {@link TeamspeakUserEntity} объект для обновления / сущность пользователя
     * @param <T>  объект для обновления
     * @return {@link TeamspeakUserEntity} обновленная сущность пользователя
     */
    <T> TeamspeakUserEntity update(T user);

    /**
     * Возвращает запись по token
     *
     * @param token teamspeak-token
     * @return {@link TeamspeakUserEntity} сущность пользователя
     */
    TeamspeakUserEntity findByTeamspeakToken(String token);

    /**
     * Возвращает все записи с флагом subscribe
     *
     * @return List&lt;{@link TeamspeakUserEntity}&gt; список сущностей
     */
    List<TeamspeakUserEntity> findAllSubscribers();

    /**
     * Возвращает все записи из бд
     *
     * @return List&lt;{@link TeamspeakUserEntity}&gt; список сущностей
     */
    List<TeamspeakUserEntity> findAll();

    /**
     * Удаление записи по token пользователя
     *
     * @param token teamspeak-token
     * @return List&lt;{@link TeamspeakUserEntity}&gt; список сущностей
     */
    List<TeamspeakUserEntity> deleteByTeamspeakToken(String token);

    /**
     * Convert entity to Dto object
     *
     * @param entity {@link TeamspeakUserEntity}
     * @return {@link TeamspeakUserEntityDataTransferObject}
     */
    TeamspeakUserEntityDataTransferObject convertToDataTransferObject(TeamspeakUserEntity entity);

    /**
     * Convert Dto object to entity
     *
     * @param dto {@link TeamspeakUserEntityDataTransferObject}
     * @return {@link TeamspeakUserEntity}
     */
    TeamspeakUserEntity convertFromDataTransferObject(TeamspeakUserEntityDataTransferObject dto);
}
