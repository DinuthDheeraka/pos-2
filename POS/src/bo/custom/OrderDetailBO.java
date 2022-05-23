package bo.custom;

import bo.SuperBO;
import dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    void insertOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> getOrderDetail(String orderId);
}
