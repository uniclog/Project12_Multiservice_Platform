package local.uniclog.mainframe.dao.telegram.dto;

import local.uniclog.mainframe.dao.telegram.entity.TelegramTORGUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for {@link TelegramTORGUserEntity}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramTORGUserEntityDto {
    /**
     * DataBase-Id пользователя
     */
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
    private Boolean subscriber;
}
