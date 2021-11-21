package local.uniclog.frame_dataaccess.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "telegram_unic_bot_core_users")
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
        if (o == null || getClass() != o.getClass()) return false;
        TelegramUnicBotCoreUserEntity that = (TelegramUnicBotCoreUserEntity) o;
        return id.equals(that.id) && userTelegramId.equals(that.userTelegramId) && userName.equals(that.userName) && subscriber.equals(that.subscriber);
    }

    @Override
    public int hashCode() {
        return 353605777;
    }
}
