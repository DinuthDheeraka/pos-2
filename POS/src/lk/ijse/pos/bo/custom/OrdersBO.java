package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {

    void insertOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;
    String getLastOrderId() throws SQLException, ClassNotFoundException;
    int getOrdersCount() throws SQLException, ClassNotFoundException;
    int getOrderCountForMonthOf(String month) throws SQLException, ClassNotFoundException;
    int getOrderCountByCustomerId(String customerId) throws SQLException, ClassNotFoundException;
    int getCustomerOrderCountByDate(String customerId,String date) throws SQLException, ClassNotFoundException;
    ArrayList<String> getOrderIdsByCustomerId(String custId) throws SQLException, ClassNotFoundException;
}
