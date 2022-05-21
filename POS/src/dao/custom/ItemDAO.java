package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    void subtractItemQOH(int amount) throws SQLException, ClassNotFoundException;
}
