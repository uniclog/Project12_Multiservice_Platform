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
@Table(name = "telegram_my_fitness_users")
public class TelegramMyFitnessUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userTelegramId;
    private String userName;
    private Boolean subscriber;
    private Integer waterCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramMyFitnessUserEntity that = (TelegramMyFitnessUserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userTelegramId, that.userTelegramId)
                && Objects.equals(userName, that.userName) && Objects.equals(subscriber, that.subscriber)
                && Objects.equals(waterCount, that.waterCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userTelegramId, userName, subscriber, waterCount);
    }
}
