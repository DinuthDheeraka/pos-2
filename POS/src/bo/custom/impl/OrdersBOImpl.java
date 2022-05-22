package bo.custom.impl;

import bo.custom.OrdersBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dto.OrdersDTO;
import entity.Orders;

import java.sql.SQLException;

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
}
