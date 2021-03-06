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

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(OrderId) FROM Orders;");
        return resultSet.next()? resultSet.getInt(1) : 0;
    }

    @Override
    public int getOrderCountForMonthOf(String month) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(OrderId) FROM Orders WHERE Date LIKE ?",month);
        return resultSet.next()? resultSet.getInt(1) : 0;
    }

    @Override
    public int getOrderCountByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(OrderId) FROM orders WHERE CustID = ?;",customerId);
        return resultSet.next()? resultSet.getInt(1) : 0;
    }

    @Override
    public int getCustomerOrderCountByDate(String customerId,String date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(OrderId) FROM orders WHERE CustID = ? AND Date LIKE ?;",customerId,date);
        return resultSet.next()? resultSet.getInt(1) : 0;
    }

    @Override
    public ArrayList<String> getOrderIdsByCustomerId(String custId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT OrderId FROM orders WHERE CustID = ?;",custId);
        ArrayList<String> orderIds = new ArrayList();
        while(resultSet.next()){
            orderIds.add(resultSet.getString(1));
        }
        return orderIds;
    }
}
