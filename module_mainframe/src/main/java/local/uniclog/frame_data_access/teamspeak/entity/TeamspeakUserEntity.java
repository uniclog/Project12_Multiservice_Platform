package local.uniclog.frame_data_access.teamspeak.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Scope("prototype")
@Table(name = "teamspeak_users")
public class TeamspeakUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String teamspeakToken;
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
