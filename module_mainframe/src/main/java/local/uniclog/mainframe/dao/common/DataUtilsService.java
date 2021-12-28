package local.uniclog.mainframe.dao.common;

import java.lang.reflect.Type;

public interface DataUtilsService {
    <E, T>T convertToDataTransferObject(E entity, Type type);
    <E, T>T convertFromDataTransferObject(E dto, Type type);
}
