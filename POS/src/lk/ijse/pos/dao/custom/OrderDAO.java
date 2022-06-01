package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Orders;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders,String> {
    int getOrderCountForMonthOf(String month) throws SQLException, ClassNotFoundException;
    int getOrderCountByCustomerId(String customerId) throws SQLException, ClassNotFoundException;
}
