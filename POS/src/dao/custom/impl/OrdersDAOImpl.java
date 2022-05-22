package dao.custom.impl;

import dao.custom.OrderDAO;
import dto.OrdersDTO;
import entity.Orders;
import util.CrudUtil;

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
        return null;
    }
}
