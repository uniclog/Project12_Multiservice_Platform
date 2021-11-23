package local.uniclog.frame_data_access.telegram.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "telegram_torg_users")
public class TelegramTORGUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userTelegramId;
    private String userName;
    private Boolean subscriber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramTORGUserEntity that = (TelegramTORGUserEntity) o;
        return id.equals(that.id) && userTelegramId.equals(that.userTelegramId)
                && userName.equals(that.userName) && subscriber.equals(that.subscriber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userTelegramId, userName, subscriber);
    }
}