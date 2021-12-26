package local.uniclog.mainframe.dao.common;

public interface DataUtilsService {
    <E, T>T convertToDataTransferObject(E entity, T dtoClassType);
    <E, T>T convertFromDataTransferObject(E dto, T entityClassType);
}
