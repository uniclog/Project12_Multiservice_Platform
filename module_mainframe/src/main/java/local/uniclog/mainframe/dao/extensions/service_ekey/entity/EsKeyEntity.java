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
 *         <li>Ключ (primary key) {@link EsKeyEntity#keyValue}</li>
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
    private String keyValue;
    /** Дата создания ключа (генерируется при создании сушности) */
    private LocalDateTime date = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsKeyEntity entity = (EsKeyEntity) o;
        return Objects.equals(keyValue, entity.keyValue) && Objects.equals(date, entity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyValue, date);
    }
}