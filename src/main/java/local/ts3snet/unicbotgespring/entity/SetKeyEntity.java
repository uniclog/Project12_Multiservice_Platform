package local.ts3snet.unicbotgespring.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "keys")
public class SetKeyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    @Id
    String key;
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SetKeyEntity that = (SetKeyEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 903267378;
    }
}