package local.uniclog.mainframe.dao.extensions.service_ekey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for {@link EsKeyEntityDto}
 *
 * @version 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsKeyEntityDto {
    /**
     * Поле ключа
     */
    private String keyValue;
    /**
     * Дата создания ключа
     */
    private LocalDateTime date;
}