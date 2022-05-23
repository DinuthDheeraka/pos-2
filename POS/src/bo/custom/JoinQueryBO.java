package bo.custom;

import dto.CustomDTO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface JoinQueryBO {
    ArrayList<CustomDTO> getOrderDetailByOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
