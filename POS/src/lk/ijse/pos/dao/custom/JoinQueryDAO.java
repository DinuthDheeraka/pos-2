package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface JoinQueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getOrderDetailByOrderId(String orderId) throws SQLException, ClassNotFoundException;
    CustomEntity getOrderByOrderId(String orderId) throws SQLException, ClassNotFoundException;
    double getIncomeByYearForEachMonth(String year) throws SQLException, ClassNotFoundException;
    public double getDiscountByYear(String year) throws SQLException, ClassNotFoundException;
    public double getTotalIncomeByYear(String year) throws SQLException, ClassNotFoundException;
}
