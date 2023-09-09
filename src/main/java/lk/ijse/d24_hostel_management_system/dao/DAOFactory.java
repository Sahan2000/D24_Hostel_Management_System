package lk.ijse.d24_hostel_management_system.dao;

import lk.ijse.d24_hostel_management_system.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.d24_hostel_management_system.dao.custom.impl.RoomDAOImpl;
import lk.ijse.d24_hostel_management_system.dao.custom.impl.StudentDAOImpl;
import lk.ijse.d24_hostel_management_system.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER,STUDENT,ROOM,RESERVATION
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }
}
