package local.uniclog.frame_data_access.telegram.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность пользователя MyFitnessTelegramUser
 * <ul>
 *         <li>Id (primary key) {@link TelegramMyFitnessUserEntity#id}</li>
 *         <li>Telegram-Id {@link TelegramMyFitnessUserEntity#userTelegramId}</li>
 *         <li>Поле имя пользователя {@link TelegramMyFitnessUserEntity#userName}</li>
 *         <li>Флаг является ли пользователь подписчиком {@link TelegramMyFitnessUserEntity#subscriber}</li>
 *         <li>Поле содержит количество выпитых стаканов {@link TelegramMyFitnessUserEntity#waterCount}</li>
 * </ul>
 *
 * @author uniclog
 * @version 0.1
 */
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
    /**
     * Telegram-Id пользователя
     */
    private Long userTelegramId;
    /**
     * Поле имя пользователя
     */
    private String userName;
    /**
     * Флаг подписки на бота
     */
    private Boolean subscriber = false;
    /**
     * Поле содержит количество выпитых стаканов
     */
    private Integer waterCount = 0;

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
