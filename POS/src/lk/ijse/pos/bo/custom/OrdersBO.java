package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrdersDTO;

import java.sql.SQLException;

public interface OrdersBO extends SuperBO {

    void insertOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;
    String getLastOrderId() throws SQLException, ClassNotFoundException;
    int getOrdersCount() throws SQLException, ClassNotFoundException:
}
