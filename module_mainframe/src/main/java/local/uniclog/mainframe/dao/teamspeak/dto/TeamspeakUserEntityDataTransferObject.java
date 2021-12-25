package local.uniclog.mainframe.dao.teamspeak.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamspeakUserEntityDataTransferObject {
    private Integer id;
    /**
     * Teamspeak-token пользователя
     */
    private String teamspeakToken;
    /**
     * Флаг подписки на бота
     */
    private Boolean subscriber;
}
