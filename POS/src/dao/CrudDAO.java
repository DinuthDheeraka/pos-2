package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <DTO,ID>{

    public ArrayList<DTO> getAll() throws SQLException,ClassNotFoundException;
}
