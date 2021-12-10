package local.uniclog.mainframe.dao.teamspeak.service;

import local.uniclog.mainframe.dao.teamspeak.entity.TeamspeakUserEntity;

import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности {@link TeamspeakUserEntity}
 * <ul>
 * <li>сохранение сущности в бд {@link TeamspeakUserEntityDataService#save(TeamspeakUserEntity)}</li>
 * <li>обновление полей для записи {@link TeamspeakUserEntityDataService#update(TeamspeakUserEntity)}</li>
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
     * @param user {@link TeamspeakUserEntity} сущность пользователя
     */
    void save(TeamspeakUserEntity user);

    /**
     * Обновление полей для записи
     *
     * @param user {@link TeamspeakUserEntity} сущность пользователя
     */
    void update(TeamspeakUserEntity user);

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
}
