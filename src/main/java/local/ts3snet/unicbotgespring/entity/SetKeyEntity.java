package local.ts3snet.unicbotgespring.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;


@Getter
@Setter
@ToString
@Entity(name = "keys")
public class SetKeyEntity {
    @Id
    String key;

    private Date date;

    public SetKeyEntity() {
        this.setDate(new Date(System.currentTimeMillis()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SetKeyEntity keyEntity = (SetKeyEntity) o;

        return Objects.equals(key, keyEntity.key);
    }

    @Override
    public int hashCode() {
        return 1996676398;
    }
}