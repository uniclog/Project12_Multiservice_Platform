package local.uniclog.frame_data_access.extensions.service_ekey.service;

import local.uniclog.frame_data_access.extensions.service_ekey.entity.EsKeyEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс сервиса работы с репозиторием
 * для сущности ключей {@link EsKeyEntity}
 * <li>сохранение {@link EsKeyEntityDataService#save(EsKeyEntity)}</li>
 * <li>удаление {@link EsKeyEntityDataService#delete(EsKeyEntity)}</li>
 * <li>удаление всех записей {@link EsKeyEntityDataService#deleteAll()}</li>
 * <li>удаление по ключу {@link EsKeyEntityDataService#deleteByKey(String)}</li>
 * <li>возвращает все записи дата которых после {@link EsKeyEntityDataService#findByDateAfter(LocalDateTime)}</li>
 * <li>возвращает все записи {@link EsKeyEntityDataService#findAll()}</li>
 * <li>возвращает запись по ключ {@link EsKeyEntityDataService#findByKey(String)}</li>
 *
 * @author uniclog
 * @version 0.1
 */
public interface EsKeyEntityDataService {
    void save(EsKeyEntity key);
    void delete(EsKeyEntity key);
    void deleteAll();
    EsKeyEntity deleteByKey(String key);
    List<EsKeyEntity> findByDateAfter(LocalDateTime date);
    List<EsKeyEntity> findAll();
    EsKeyEntity findByKey(String key);
}
