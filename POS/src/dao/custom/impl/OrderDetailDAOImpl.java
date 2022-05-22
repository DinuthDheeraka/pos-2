package dao.custom.impl;

import dao.custom.OrderDetailDAO;
import entity.OrderDetail;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void insert(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("INSERT INTO OrderDetail VALUES(?,?,?,?,?)",
                orderDetail.getOrderId(),orderDetail.getItemCode(),orderDetail.getOrderQTY(),
                orderDetail.getUnitPrice(),orderDetail.getDiscount())){

        }
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetail get(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
