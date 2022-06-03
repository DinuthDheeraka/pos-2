package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    void subtractItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException;
    void increasetItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException;
    double getItemsBelowFiftyUnits() throws SQLException, ClassNotFoundException;
    double getItemsBetweenFiftyAndOneFiftyUnits() throws SQLException, ClassNotFoundException;
    double getItemsThatHaveMorethanOneFiftyUnits() throws SQLException, ClassNotFoundException;
}
