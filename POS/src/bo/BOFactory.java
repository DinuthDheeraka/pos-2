package bo;

import bo.custom.impl.CustomerBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory==null? boFactory = new BOFactory() : boFactory;
    }

    public enum BO{
        CUSTOMERBO_IMPL
    }

    public SuperBO getBO(BO boType){
        switch (boType){
            case CUSTOMERBO_IMPL:return new CustomerBOImpl();
            default:return null;
        }
    }
}
