package lk.ijse.pos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T,ID> extends SuperDAO{

    ArrayList<T> getAll() throws SQLException,ClassNotFoundException;
    void insert(T t) throws SQLException,ClassNotFoundException;
    void delete(ID id) throws SQLException,ClassNotFoundException;
    void update(T t) throws SQLException,ClassNotFoundException;
    ArrayList<ID> getAllIds() throws SQLException, ClassNotFoundException;
    T get(ID id) throws SQLException, ClassNotFoundException;
    ID getLastId() throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
}
