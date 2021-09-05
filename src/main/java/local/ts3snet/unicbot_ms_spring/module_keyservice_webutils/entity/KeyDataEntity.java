package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

/**
 * Key Entity from web chat
 * @author erlidione
 */
@Getter
@Setter
@ToString
@Entity(name = "keys")
public class KeyDataEntity {
    @Id
    private String key;
    private Date date;

    public KeyDataEntity() {
        this.setDate(new Date(System.currentTimeMillis()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        KeyDataEntity keyEntity = (KeyDataEntity) o;

        return Objects.equals(key, keyEntity.key);
    }

    @Override
    public int hashCode() {
        return 1996676398;
    }
}