package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <DTO,ID> extends SuperDAO{

    public ArrayList<DTO> getAll() throws SQLException,ClassNotFoundException;
}
