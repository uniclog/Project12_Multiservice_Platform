package local.uniclog.mainframe.dao.telegram.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Сущность пользователя TelegramTORGUser
 * <ul>
 *         <li>Id (primary key) {@link TelegramTORGUserEntity#id}</li>
 *         <li>Telegram-Id {@link TelegramTORGUserEntity#userTelegramId}</li>
 *         <li>Поле имя пользователя {@link TelegramTORGUserEntity#userName}</li>
 *         <li>Флаг является ли пользователь подписчиком {@link TelegramTORGUserEntity#subscriber}</li>
 * </ul>
 *
 * @author uniclog
 * @version 0.1
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "telegram_torg_users")
public class TelegramTORGUserEntity {
    @Id
    @GeneratedValue
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramTORGUserEntity that = (TelegramTORGUserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userTelegramId, that.userTelegramId)
                && Objects.equals(userName, that.userName) && Objects.equals(subscriber, that.subscriber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userTelegramId, userName, subscriber);
    }
}