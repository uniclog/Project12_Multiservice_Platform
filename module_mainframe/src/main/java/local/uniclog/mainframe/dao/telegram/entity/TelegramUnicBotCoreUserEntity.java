package local.uniclog.mainframe.dao.telegram.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность пользователя UnicBotTelegramUser
 * <ul>
 *         <li>Id (primary key) {@link TelegramUnicBotCoreUserEntity#id}</li>
 *         <li>Telegram-Id {@link TelegramUnicBotCoreUserEntity#userTelegramId}</li>
 *         <li>Поле имя пользователя {@link TelegramUnicBotCoreUserEntity#userName}</li>
 *         <li>Флаг является ли пользователь подписчиком {@link TelegramUnicBotCoreUserEntity#subscriber}</li>
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
@Table(name = "telegram_unic_bot_core_users")
public class TelegramUnicBotCoreUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        TelegramUnicBotCoreUserEntity that = (TelegramUnicBotCoreUserEntity) o;
        return id.equals(that.id) && userTelegramId.equals(that.userTelegramId)
                && userName.equals(that.userName) && subscriber.equals(that.subscriber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userTelegramId, userName, subscriber);
    }
}
