package local.uniclog.frame_data_access.extensions.service_ekey.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Сущность для ключей
 *         <li>Ключ, уникальное полк {@link EsKeyEntity#key}</li>
 *         <li>Дата создание {@link EsKeyEntity#date}</li>
 * @author uniclog
 * @version 0.1
 */
@Getter
@Setter
@ToString
@Entity(name = "service_eskey")
public class EsKeyEntity {
    /** Поле ключа */
    @Id
    private String key;
    /** Дата создания ключа (генерируется при создании сушности) */
    private LocalDateTime date;

    /**
     * Задает дату при создании ключа
     */
    public EsKeyEntity() {
        this.setDate(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsKeyEntity that = (EsKeyEntity) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return 1996676398;
    }
}