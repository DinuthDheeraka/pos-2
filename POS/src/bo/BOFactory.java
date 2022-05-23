package bo;

import bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory==null? boFactory = new BOFactory() : boFactory;
    }

    public enum BO{
        CUSTOMERBO_IMPL,ITEMBO_IMPL,ORDERBO_IMPL,ORDERDETAILBO_IMPL,JOINQUERYBO_IMPL
    }

    public SuperBO getBO(BO boType){
        switch (boType){
            case CUSTOMERBO_IMPL:return new CustomerBOImpl();
            case ITEMBO_IMPL:return new ItemBOImpl();
            case ORDERBO_IMPL:return new OrdersBOImpl();
            case ORDERDETAILBO_IMPL:return new OrderDetailBOImpl();
            case JOINQUERYBO_IMPL:return new JoinQueryBOImpl();
            default:return null;
        }
    }
}
