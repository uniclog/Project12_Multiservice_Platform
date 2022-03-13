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
     * Objects convert
     *
     * @param object object
     * @param type   entity type
     * @param <E>    entity type
     * @param <T>    dto type
     * @return Dto object
     */
    @Override
    public <E, T> T convertData(E object, Type type) {
        return Objects.isNull(object) ? null : mapper.map(object, type);
    }
}
