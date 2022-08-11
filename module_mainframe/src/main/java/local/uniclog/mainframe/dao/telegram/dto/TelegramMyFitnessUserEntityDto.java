package local.uniclog.mainframe.dao.telegram.dto;

import local.uniclog.mainframe.dao.telegram.entity.TelegramMyFitnessUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for {@link TelegramMyFitnessUserEntity}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramMyFitnessUserEntityDto {
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
    /**
     * Поле содержит количество выпитых стаканов
     */
    private Integer waterCount;
}
