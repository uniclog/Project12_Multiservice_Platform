package local.uniclog.mainframe.dao.teamspeak.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность пользователя teamspeak
 * <ul>
 *         <li>Id (primary key) {@link TeamspeakUserEntity#id}</li>
 *         <li>Teamspeak-token {@link TeamspeakUserEntity#teamspeakToken}</li>
 *         <li>Является пользователь подписчиком {@link TeamspeakUserEntity#subscriber}</li>
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
@Table(name = "teamspeak_users")
public class TeamspeakUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Teamspeak-token пользователя
     */
    private String teamspeakToken;
    /**
     * Флаг подписки на бота
     */
    private Boolean subscriber = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamspeakUserEntity that = (TeamspeakUserEntity) o;
        return id.equals(that.id) && teamspeakToken.equals(that.teamspeakToken) && subscriber.equals(that.subscriber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamspeakToken, subscriber);
    }
}
