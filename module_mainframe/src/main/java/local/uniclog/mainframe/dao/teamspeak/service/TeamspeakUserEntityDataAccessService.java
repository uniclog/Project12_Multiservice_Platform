package local.uniclog.mainframe.dao.teamspeak.service;

import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDto;

import java.util.List;

/**
 * Data Access Service for TeamspeakUserEntity
 *
 * @author uniclog
 * @version 0.1
 */
public interface TeamspeakUserEntityDataAccessService {
    /**
     * Обертка для сохранения сущности в бд
     *
     * @param object user {@link Object}
     * @param <T>    объект для метода save
     * @return {@link TeamspeakUserEntityDto}
     */
    <T> TeamspeakUserEntityDto save(T object);

    /**
     * Обертка для обновления полей записи
     *
     * @param object {@link Object}
     * @param <T>    объект для метода update
     * @return {@link TeamspeakUserEntityDto}
     */
    <T> TeamspeakUserEntityDto update(T object);

    /**
     * Обертка для получения пользователя по его token
     *
     * @param token teamspeak-token
     * @return {@link TeamspeakUserEntityDto}
     */
    TeamspeakUserEntityDto findByTeamspeakToken(String token);

    /**
     * Обертка для получения всх записей с флагом subscribe
     *
     * @return List&lt;{@link TeamspeakUserEntityDto}&gt;
     */
    List<TeamspeakUserEntityDto> findAllSubscribers();

    /**
     * Обертка для получения всех записей из бд
     *
     * @return List&lt;{@link TeamspeakUserEntityDto}&gt;
     */
    List<TeamspeakUserEntityDto> findAll();

    /**
     * Обертка для удаления записи по token
     *
     * @param token teamspeak-token
     * @return List&lt;{@link TeamspeakUserEntityDto}&gt;
     */
    List<TeamspeakUserEntityDto> deleteByTeamspeakToken(String token);
}
