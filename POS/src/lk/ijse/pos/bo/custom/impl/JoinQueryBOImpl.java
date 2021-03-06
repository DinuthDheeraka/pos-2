package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.JoinQueryBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.JoinQueryDAO;
import lk.ijse.pos.dto.CustomDTO;
import lk.ijse.pos.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class JoinQueryBOImpl implements JoinQueryBO {
    JoinQueryDAO joinQueryDAO = (JoinQueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAO.JOINQUERY_IMPL);

    @Override
    public ArrayList<CustomDTO> getOrderDetailByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> customDTOS = new ArrayList();
        ArrayList<CustomEntity> customEntities =  joinQueryDAO.getOrderDetailByOrderId(orderId);
        for(CustomEntity customEntity : customEntities){
            customDTOS.add(new CustomDTO(
                    customEntity.getOrderDetailitemCode(), customEntity.getDescription(),
                    customEntity.getPackSize(),customEntity.getOrderDetailunitPrice(),
                    customEntity.getOrderDetailorderQTY(),customEntity.getOrderDetaildiscount()
            ));
        }
        return customDTOS;
    }

    @Override
    public CustomDTO getOrderByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        CustomEntity customEntity = joinQueryDAO.getOrderByOrderId(orderId);
        if(customEntity!=null){
            return new CustomDTO(
                  customEntity.getCustName(),customEntity.getOrdersOrderId(),customEntity.getOrdersDate(),
                  customEntity.getOrdersCustID()
            );
        }else{
            return null;
        }
    }

    @Override
    public double getIncomeByYearForEachMonth(String year) throws SQLException, ClassNotFoundException {
        return joinQueryDAO.getIncomeByYearForEachMonth(year);
    }

    @Override
    public double getDiscountByYear(String year) throws SQLException, ClassNotFoundException {
        return joinQueryDAO.getDiscountByYear(year);
    }

    @Override
    public double getTotalIncomeByYear(String year) throws SQLException, ClassNotFoundException {
        return joinQueryDAO.getTotalIncomeByYear(year);
    }

    @Override
    public double getTotalOrderQTYByDateLike(String itemCode, String date) throws SQLException, ClassNotFoundException {
        return joinQueryDAO.getTotalOrderQTYByDateLike(itemCode,date);
    }

    @Override
    public LinkedList<CustomDTO> getSalesByDateForEachItemOrderBySalesDESC(String date) throws SQLException, ClassNotFoundException {
        LinkedList<CustomEntity> customEntities = joinQueryDAO.getSalesByDateForEachItemOrderBySalesDESC(date);
        LinkedList<CustomDTO> customDTOS = new LinkedList();

        for(CustomEntity customEntity : customEntities){
            customDTOS.add(new CustomDTO(
                    customEntity.getItemCode(),customEntity.getDescription(),
                    customEntity.getPackSize(),customEntity.getTotalSales()
            ));
        }

        return customDTOS;
    }
}
