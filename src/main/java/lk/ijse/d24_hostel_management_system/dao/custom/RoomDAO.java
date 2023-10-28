package lk.ijse.d24_hostel_management_system.dao.custom;

import lk.ijse.d24_hostel_management_system.dao.CrudDAO;
import lk.ijse.d24_hostel_management_system.entity.Room;

public interface RoomDAO extends CrudDAO<Room> {

    String getCount();

    int getAcRoomValue();

    int getNonACRoomValue();

    int getACFoodRoomValue();

    int getNonACFoodValue();
}
