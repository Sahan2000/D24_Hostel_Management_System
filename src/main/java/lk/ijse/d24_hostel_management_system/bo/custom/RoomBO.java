package lk.ijse.d24_hostel_management_system.bo.custom;

import lk.ijse.d24_hostel_management_system.bo.SuperBO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    boolean saveRooms(RoomDTO roomDTO);

    boolean updateRooms(RoomDTO roomDTO);

    List<RoomDTO> getAllRooms();

    boolean deleteRoom(String roomTypeId);

    String generatenextRoomId();
}
