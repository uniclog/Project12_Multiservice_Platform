package local.ts3snet.unicbot_ms_spring.module_keyservice_webutils.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
    private LocalDateTime date;

    public KeyDataEntity() {
        this.setDate(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyDataEntity that = (KeyDataEntity) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return 1996676398;
    }
}