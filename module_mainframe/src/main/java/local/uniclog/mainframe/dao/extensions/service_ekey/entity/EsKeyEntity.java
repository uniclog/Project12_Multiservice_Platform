package local.uniclog.mainframe.dao.extensions.service_ekey.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Сущность для ключей
 *      <ul>
 *         <li>Ключ (primary key) {@link EsKeyEntity#key}</li>
 *         <li>Дата создание {@link EsKeyEntity#date}</li>
 *      </ul>
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
        return key.equals(that.key) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, date);
    }
}