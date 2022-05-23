package dao.custom;

import dao.SuperDAO;
import dto.CustomDTO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface JoinQueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getOrderDetailByOrderId(String orderId) throws SQLException, ClassNotFoundException;
    CustomEntity getOrderByOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
