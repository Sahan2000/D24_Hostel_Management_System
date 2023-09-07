package lk.ijse.d24_hostel_management_system.bo.custom;

import lk.ijse.d24_hostel_management_system.bo.SuperBO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;

public interface RoomBO extends SuperBO {
    boolean saveRooms(RoomDTO roomDTO);

    boolean updateRooms(RoomDTO roomDTO);
}
