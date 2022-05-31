package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dto.CustomDTO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public interface JoinQueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getOrderDetailByOrderId(String orderId) throws SQLException, ClassNotFoundException;
    CustomEntity getOrderByOrderId(String orderId) throws SQLException, ClassNotFoundException;
    double getIncomeByYearForEachMonth(String year) throws SQLException, ClassNotFoundException;
    double getDiscountByYear(String year) throws SQLException, ClassNotFoundException;
    double getTotalIncomeByYear(String year) throws SQLException, ClassNotFoundException;
    double getTotalOrderQTYByDateLike(String itemCode,String date) throws SQLException, ClassNotFoundException;
    LinkedList<CustomEntity> getSalesByDateForEachItemOrderBySalesDESC(String date) throws SQLException, ClassNotFoundException;
}
