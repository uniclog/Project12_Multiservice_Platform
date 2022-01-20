package local.uniclog.mainframe.dao.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Data Services
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataUtilsServiceImpl implements DataUtilsService {
    private final ModelMapper mapper;

    /**
     * Convert to Dto
     *
     * @param entity entity
     * @param type   entity type
     * @param <E>    entity type
     * @param <T>    dto type
     * @return Dto object
     */
    @Override
    public <E, T> T convertToDataTransferObject(E entity, Type type) {
        return Objects.isNull(entity) ? null : mapper.map(entity, type);
    }

    /**
     * Convert from Dto
     *
     * @param dto  data transfer object
     * @param type entity type
     * @param <E>  dto type
     * @param <T>  entity type
     * @return Entity object
     */
    @Override
    public <E, T> T convertFromDataTransferObject(E dto, Type type) {
        return Objects.isNull(dto) ? null : mapper.map(dto, type);
    }
}
