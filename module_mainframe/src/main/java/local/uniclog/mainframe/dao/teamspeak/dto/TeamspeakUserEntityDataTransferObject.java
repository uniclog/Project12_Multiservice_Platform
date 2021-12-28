package local.uniclog.mainframe.dao.teamspeak.dto;

import local.uniclog.mainframe.dao.teamspeak.entity.TeamspeakUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for {@link TeamspeakUserEntity}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamspeakUserEntityDataTransferObject {
    /**
     * User id
     */
    private Integer id;
    /**
     * User teamspeak-token
     */
    private String teamspeakToken;
    /**
     * Subscriber flag
     */
    private Boolean subscriber;
}
