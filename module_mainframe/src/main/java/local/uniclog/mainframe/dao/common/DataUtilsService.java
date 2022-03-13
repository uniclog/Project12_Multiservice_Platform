package local.uniclog.mainframe.dao.common;

import java.lang.reflect.Type;

public interface DataUtilsService {
    <E, T> T convertData(E dto, Type type);
}
