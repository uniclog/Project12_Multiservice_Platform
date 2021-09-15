package local.ts3snet.unicbot_ms_spring.core.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "telegramUnicBotCoreUsers")
public class TelegramUnicBotCoreUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userTelegramId;
    private String userName;
    private Boolean subscriber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TelegramUnicBotCoreUserEntity that = (TelegramUnicBotCoreUserEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 353605777;
    }
}
