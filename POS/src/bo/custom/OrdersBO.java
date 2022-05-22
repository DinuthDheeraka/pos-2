package bo.custom;

import dto.OrdersDTO;

import java.sql.SQLException;

public interface OrdersBO {

    void insertOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;
}
