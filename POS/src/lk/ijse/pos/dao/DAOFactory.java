package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory==null? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAO{
        CUSTOMER_IMPL,ITEM_IMPL,ORDERS_IMPL,ORDERDETAIL_IMPL,JOINQUERY_IMPL
    }

    public SuperDAO getDAO(DAO daoType){
        switch (daoType){
            case CUSTOMER_IMPL:return new CustomerDAOImpl();
            case ITEM_IMPL:return new ItemDAOImpl();
            case ORDERS_IMPL:return new OrdersDAOImpl();
            case ORDERDETAIL_IMPL:return new OrderDetailDAOImpl();
            case JOINQUERY_IMPL:return new JoinQueryDAOImpl();
            default:return null;
        }
    }
}
