package lk.ijse.d24_hostel_management_system.bo;

import lk.ijse.d24_hostel_management_system.bo.custom.impl.StudentBOImpl;
import lk.ijse.d24_hostel_management_system.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,STUDENT
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
