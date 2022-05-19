package dao;

import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory==null? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAO{
        CUSTOMER_IMPL,ITEM_IMPL
    }

    public SuperDAO getDAO(DAO daoType){
        switch (daoType){
            case CUSTOMER_IMPL:return new CustomerDAOImpl();
            case ITEM_IMPL:return new ItemDAOImpl();
            default:return null;
        }
    }
}
