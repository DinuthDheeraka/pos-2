package bo.custom;

import bo.SuperBO;
import dto.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailBO extends SuperBO {
    void insertOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;
}
