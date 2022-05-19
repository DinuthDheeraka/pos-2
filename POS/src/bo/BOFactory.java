package bo;

import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory==null? boFactory = new BOFactory() : boFactory;
    }

    public enum BO{
        CUSTOMERBO_IMPL,ITEMBO_IMPL
    }

    public SuperBO getBO(BO boType){
        switch (boType){
            case CUSTOMERBO_IMPL:return new CustomerBOImpl();
            case ITEMBO_IMPL:return new ItemBOImpl();
            default:return null;
        }
    }
}
