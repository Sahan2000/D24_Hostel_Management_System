package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import lk.ijse.d24_hostel_management_system.bo.custom.RoomBO;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.RoomDAO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.entity.Room;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public boolean saveRooms(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoom_type_id(roomDTO.getRoom_type_id());
        room.setType(roomDTO.getType());
        room.setKey_money(roomDTO.getKey_money());
        room.setType(roomDTO.getType());
        return roomDAO.save(room);
    }

    @Override
    public boolean updateRooms(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoom_type_id(roomDTO.getRoom_type_id());
        room.setType(roomDTO.getType());
        room.setKey_money(roomDTO.getKey_money());
        room.setType(roomDTO.getType());
        return roomDAO.update(room);
    }


}
