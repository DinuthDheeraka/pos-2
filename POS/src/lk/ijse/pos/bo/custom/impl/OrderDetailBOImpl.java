package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderDetailBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAO.ORDERDETAIL_IMPL);

    @Override
    public void insertOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        orderDetailDAO.insert(new OrderDetail(
                orderDetailDTO.getOrderId(),orderDetailDTO.getItemCode(),
                orderDetailDTO.getOrderQTY(),orderDetailDTO.getUnitPrice(),
                orderDetailDTO.getDiscount()
        ));
    }

    @Override
    public ArrayList<OrderDetailDTO> getOrderDetail(String orderId) {
        return null;
    }
}
