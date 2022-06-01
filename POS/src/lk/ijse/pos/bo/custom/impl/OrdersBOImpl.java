package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrdersBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dto.OrdersDTO;
import lk.ijse.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO{
    private OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAO.ORDERS_IMPL);

    @Override
    public void insertOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
        orderDAO.insert(new Orders(
                ordersDTO.getOrderId(), ordersDTO.getDate(),ordersDTO.getCustId()
        ));
    }

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getLastId();
    }

    @Override
    public int getOrdersCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getCount();
    }

    @Override
    public int getOrderCountForMonthOf(String month) throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderCountForMonthOf(month);
    }

    @Override
    public int getOrderCountByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderCountByCustomerId(customerId);
    }

    @Override
    public int getCustomerOrderCountByDate(String customerId, String date) throws SQLException, ClassNotFoundException {
        return orderDAO.getCustomerOrderCountByDate(customerId,date);
    }

    @Override
    public ArrayList<String> getOrderIdsByCustomerId(String custId) throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderIdsByCustomerId(custId);
    }
}
