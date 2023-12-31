package lk.ijse.d24_hostel_management_system.bo;

import lk.ijse.d24_hostel_management_system.bo.custom.impl.ReservationBOImpl;
import lk.ijse.d24_hostel_management_system.bo.custom.impl.RoomBOImpl;
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
        USER,STUDENT,ROOM,RESERVATION
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
