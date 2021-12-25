package local.uniclog.mainframe.dao.teamspeak.dto;

import local.uniclog.mainframe.dao.teamspeak.entity.TeamspeakUserEntity;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object for {@link TeamspeakUserEntity}
 */
@Data
@Builder
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
