package bo.custom;

import dto.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailBO {
    void insertOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;
}
