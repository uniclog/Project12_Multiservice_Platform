package local.uniclog.frame_data_access.extensions.service_ekey.service;

import local.uniclog.frame_data_access.extensions.service_ekey.entity.EsKeyEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис работы с репозиторием
 * для сущности ключей {@link EsKeyEntity}
 * <ul>
 *     <li>сохранение сущности в бд {@link EsKeyEntityDataService#save(EsKeyEntity)}</li>
 *     <li>удаление записи из бд {@link EsKeyEntityDataService#delete(EsKeyEntity)}</li>
 *     <li>удаление всех записей {@link EsKeyEntityDataService#deleteAll()}</li>
 *     <li>удаление записи по значению ключа {@link EsKeyEntityDataService#deleteByKey(String)}</li>
 *     <li>возвращает все записи дата которых после {@link EsKeyEntityDataService#findByDateAfter(LocalDateTime)}</li>
 *     <li>возвращает все записи {@link EsKeyEntityDataService#findAll()}</li>
 *     <li>возвращает запись по значению ключа {@link EsKeyEntityDataService#findByKey(String)}</li>
 * </ul>
 *
 * @author uniclog
 * @version 0.1
 * @see EsKeyEntity
 */
public interface EsKeyEntityDataService {
    /**
     * Сохранение сущности в бд
     *
     * @param key {@link EsKeyEntity} сущность ключей
     */
    void save(EsKeyEntity key);

    /**
     * Удаление записи из бд
     *
     * @param key {@link EsKeyEntity} сущность ключей
     */
    void delete(EsKeyEntity key);

    /**
     * Удаление всех записей в таблице
     */
    void deleteAll();

    /**
     * Удаление записи по значению ключа
     *
     * @param key значение ключа
     * @return {@link EsKeyEntity} сущность ключей
     */
    EsKeyEntity deleteByKey(String key);

    /**
     * Возвращает все записи дата которых после
     *
     * @param date дата создания ключа
     * @return List &lt;{@link EsKeyEntity}&gt; список сущностей ключей
     */
    List<EsKeyEntity> findByDateAfter(LocalDateTime date);

    /**
     * Возвращает все записи
     *
     * @return List &lt;{@link EsKeyEntity}&gt; список сущностей ключей
     */
    List<EsKeyEntity> findAll();

    /**
     * Возвращает запись по значению ключа
     *
     * @param key значение ключа
     * @return {@link EsKeyEntity} сущность ключей
     */
    EsKeyEntity findByKey(String key);
}
