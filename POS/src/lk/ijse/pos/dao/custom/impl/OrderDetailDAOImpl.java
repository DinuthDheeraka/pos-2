package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
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

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public ArrayList<OrderDetail> searchOrderDetail(String orderId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public double getItemAllTimeSales(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT SUM(orderdetail.orderQTY) FROM orderdetail WHERE ItemCode = ?;",itemCode);
        return resultSet.next()? resultSet.getDouble(1):0;
    }
}
