package local.uniclog.mainframe.dao.common;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Data Services
 */
@Slf4j
@Component
public class DataUtilsServiceImpl implements DataUtilsService {
    private ModelMapper mapper;

    @Autowired
    public void setModelMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Convert to Dto
     *
     * @param entity       entity
     * @param dtoClassType entity type
     * @param <E>          entity type
     * @param <T>          dto type
     * @return Dto object
     */
    @Override
    public <E, T> T convertToDataTransferObject(E entity, T dtoClassType) {
        return Objects.isNull(entity) ? null : mapper.map(entity, (Type) dtoClassType);
    }

    /**
     * Convert from Dto
     *
     * @param dto             data transfer object
     * @param entityClassType entity type
     * @param <E>             dto type
     * @param <T>             entity type
     * @return Entity object
     */
    @Override
    public <E, T> T convertFromDataTransferObject(E dto, T entityClassType) {
        return Objects.isNull(dto) ? null : mapper.map(dto, (Type) entityClassType);
    }
}
