package local.uniclog.mainframe.dao.teamspeak.service;

import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDataTransferObject;

import java.util.List;

public interface TeamspeakUserEntityDataAccessService {
    /**
     * Обертка для сохранения сущности в бд
     *
     * @param user {@link TeamspeakUserEntityDataTransferObject}
     * @return {@link TeamspeakUserEntityDataTransferObject}
     */
    TeamspeakUserEntityDataTransferObject save(TeamspeakUserEntityDataTransferObject user);

    /**
     * Обертка для обновления полей записи
     *
     * @param user {@link TeamspeakUserEntityDataTransferObject}
     * @return {@link TeamspeakUserEntityDataTransferObject}
     */
    TeamspeakUserEntityDataTransferObject update(TeamspeakUserEntityDataTransferObject user);

    /**
     * Обертка для получения пользователя по его token
     *
     * @param token teamspeak-token
     * @return {@link TeamspeakUserEntityDataTransferObject}
     */
    TeamspeakUserEntityDataTransferObject findByTeamspeakToken(String token);

    /**
     * Обертка для получения всх записей с флагом subscribe
     *
     * @return List&lt;{@link TeamspeakUserEntityDataTransferObject}&gt;
     */
    List<TeamspeakUserEntityDataTransferObject> findAllSubscribers();

    /**
     * Обертка для получения всех записей из бд
     *
     * @return List&lt;{@link TeamspeakUserEntityDataTransferObject}&gt;
     */
    List<TeamspeakUserEntityDataTransferObject> findAll();

    /**
     * Обертка для удаления записи по token
     *
     * @param token teamspeak-token
     * @return List&lt;{@link TeamspeakUserEntityDataTransferObject}&gt;
     */
    List<TeamspeakUserEntityDataTransferObject> deleteByTeamspeakToken(String token);
}
