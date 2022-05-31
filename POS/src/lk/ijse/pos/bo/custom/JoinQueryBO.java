package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public interface JoinQueryBO extends SuperBO {
    ArrayList<CustomDTO> getOrderDetailByOrderId(String orderId) throws SQLException, ClassNotFoundException;
    CustomDTO getOrderByOrderId(String orderId) throws SQLException, ClassNotFoundException;
    public double getIncomeByYearForEachMonth(String year) throws SQLException, ClassNotFoundException;
    public double getDiscountByYear(String year) throws SQLException, ClassNotFoundException;
    public double getTotalIncomeByYear(String year) throws SQLException, ClassNotFoundException;
    double getTotalOrderQTYByDateLike(String itemCode,String date) throws SQLException, ClassNotFoundException;
    LinkedList<CustomDTO> getSalesByDateForEachItemOrderBySalesDESC(String date) throws SQLException, ClassNotFoundException;
}
