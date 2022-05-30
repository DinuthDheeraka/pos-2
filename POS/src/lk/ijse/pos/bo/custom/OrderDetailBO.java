package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    void insertOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> getOrderDetail(String orderId);
    double getItemAllTimeSales(String itemCode) throws SQLException, ClassNotFoundException;
}
