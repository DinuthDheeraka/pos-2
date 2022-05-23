package bo.custom.impl;

import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import entity.OrderDetail;

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
