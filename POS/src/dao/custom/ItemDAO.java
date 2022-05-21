package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    void subtractItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException;
    void increasetItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException;
}
