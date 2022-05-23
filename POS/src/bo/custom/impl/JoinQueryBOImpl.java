package bo.custom.impl;

import bo.custom.JoinQueryBO;
import dao.DAOFactory;
import dao.custom.JoinQueryDAO;
import dto.CustomDTO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

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
}
