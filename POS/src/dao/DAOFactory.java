package dao;

import dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory==null? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAO{
        CUSTOMERIMPL
    }

    public static SuperDAO getDAO(DAO daoType){
        switch (daoType){
            case CUSTOMERIMPL:return new CustomerDAOImpl();
            default:return null;
        }
    }
}
