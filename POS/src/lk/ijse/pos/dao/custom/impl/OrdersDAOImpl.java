package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void insert(Orders orders) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("INSERT INTO Orders VALUES(?,?,?)",
                orders.getOrderId(),orders.getDate(),orders.getCustID())){
        }
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Orders orders) throws SQLException, ClassNotFoundException {

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Orders get(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1");
        return resultSet.next()? resultSet.getString("OrderId") : null;
    }
}
