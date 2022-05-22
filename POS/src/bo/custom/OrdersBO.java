package bo.custom;

import bo.SuperBO;
import dto.OrdersDTO;

import java.sql.SQLException;

public interface OrdersBO extends SuperBO {

    void insertOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;
}
