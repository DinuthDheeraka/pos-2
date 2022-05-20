package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <DTO,ID> extends SuperDAO{

    ArrayList<DTO> getAll() throws SQLException,ClassNotFoundException;
    void insert(DTO dto) throws SQLException,ClassNotFoundException;
    void delete(ID id) throws SQLException,ClassNotFoundException;
    ID getLastId() throws SQLException, ClassNotFoundException;
}
